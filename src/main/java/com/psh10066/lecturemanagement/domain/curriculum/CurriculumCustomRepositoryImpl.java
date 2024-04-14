package com.psh10066.lecturemanagement.domain.curriculum;

import com.psh10066.lecturemanagement.presentation.dto.CurriculumInfoDTO;
import com.psh10066.lecturemanagement.presentation.dto.CurriculumListDTO;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.psh10066.lecturemanagement.domain.curriculum.QCurriculum.curriculum;
import static com.psh10066.lecturemanagement.domain.lecture.QLecture.lecture;
import static com.psh10066.lecturemanagement.domain.lecturer.QLecturer.lecturer;
import static com.psh10066.lecturemanagement.domain.lecturetocurriculum.QLectureToCurriculum.lectureToCurriculum;

@RequiredArgsConstructor
public class CurriculumCustomRepositoryImpl implements CurriculumCustomRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<CurriculumListDTO> findAllCurriculum(Long lectureId, String lecturerName, String curriculumName) {
        return queryFactory.select(Projections.constructor(CurriculumListDTO.class,
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
                lectureId != null ? lecture.lectureId.eq(lectureId) : null,
                StringUtils.isNotBlank(lecturerName) ? lecturer.lecturerName.contains(lecturerName) : null,
                StringUtils.isNotBlank(curriculumName) ? curriculum.curriculumName.contains(curriculumName) : null
            )
            .fetch();
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