package com.psh10066.lecturemanagement.domain.curriculum;

import com.psh10066.lecturemanagement.domain.user.User;
import com.psh10066.lecturemanagement.presentation.dto.CurriculumInfoDTO;
import com.psh10066.lecturemanagement.presentation.dto.CurriculumListDTO;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;

import static com.psh10066.lecturemanagement.domain.curriculum.QCurriculum.curriculum;
import static com.psh10066.lecturemanagement.domain.lecture.QLecture.lecture;
import static com.psh10066.lecturemanagement.domain.lecturer.QLecturer.lecturer;
import static com.psh10066.lecturemanagement.domain.lecturetocurriculum.QLectureToCurriculum.lectureToCurriculum;

@RequiredArgsConstructor
public class CurriculumCustomRepositoryImpl implements CurriculumCustomRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<CurriculumListDTO> findAllCurriculum(User user, Pageable pageable, Long lectureId, String lecturerName, String curriculumName) {
        List<CurriculumListDTO> fetch = queryFactory.select(Projections.constructor(CurriculumListDTO.class,
                lecture.lectureId,
                lecture.lectureName,
                lecture.lecturePath,
                lecturer.lecturerName,
                curriculum.curriculumId,
                curriculum.curriculumName
            ))
            .from(lecture)
            .join(lectureToCurriculum).on(lecture.lectureId.eq(lectureToCurriculum.lecture.lectureId))
            .join(curriculum).on(curriculum.curriculumId.eq(lectureToCurriculum.curriculum.curriculumId))
            .leftJoin(lecturer).on(lecturer.lecturerId.eq(curriculum.lecturer.lecturerId))
            .where(
                lecture.user.eq(user),
                lectureId != null ? lecture.lectureId.eq(lectureId) : null,
                StringUtils.isNotBlank(lecturerName) ? lecturer.lecturerName.contains(lecturerName) : null,
                StringUtils.isNotBlank(curriculumName) ? curriculum.curriculumName.contains(curriculumName) : null
            )
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetch();

        JPAQuery<Long> count = queryFactory.select(lecture.count())
            .from(lecture)
            .join(lectureToCurriculum).on(lecture.lectureId.eq(lectureToCurriculum.lecture.lectureId))
            .join(curriculum).on(curriculum.curriculumId.eq(lectureToCurriculum.curriculum.curriculumId))
            .leftJoin(lecturer).on(lecturer.lecturerId.eq(curriculum.lecturer.lecturerId))
            .where(
                lecture.user.eq(user),
                lectureId != null ? lecture.lectureId.eq(lectureId) : null,
                StringUtils.isNotBlank(lecturerName) ? lecturer.lecturerName.contains(lecturerName) : null,
                StringUtils.isNotBlank(curriculumName) ? curriculum.curriculumName.contains(curriculumName) : null
            );

        return PageableExecutionUtils.getPage(fetch, pageable, count::fetchOne);
    }

    @Override
    public CurriculumInfoDTO findCurriculum(Long curriculumId) {
        return queryFactory.select(Projections.constructor(CurriculumInfoDTO.class,
                lecture.lectureName,
                lecturer.lecturerId,
                lecturer.lecturerName,
                curriculum.curriculumId,
                curriculum.curriculumName
            ))
            .from(lecture)
            .join(lectureToCurriculum).on(lecture.lectureId.eq(lectureToCurriculum.lecture.lectureId))
            .join(curriculum).on(curriculum.curriculumId.eq(lectureToCurriculum.curriculum.curriculumId))
            .leftJoin(lecturer).on(lecturer.lecturerId.eq(curriculum.lecturer.lecturerId))
            .where(curriculum.curriculumId.eq(curriculumId))
            .fetchOne();
    }
}
