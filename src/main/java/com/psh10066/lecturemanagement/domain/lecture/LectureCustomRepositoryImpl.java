package com.psh10066.lecturemanagement.domain.lecture;

import com.psh10066.lecturemanagement.domain.lecture.type.LecturePlatform;
import com.psh10066.lecturemanagement.domain.user.User;
import com.psh10066.lecturemanagement.presentation.dto.LectureListDTO;
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
public class LectureCustomRepositoryImpl implements LectureCustomRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<LectureListDTO> findAllLecture(User user, String lectureName, LecturePlatform lecturePlatform) {
        return queryFactory.select(Projections.constructor(LectureListDTO.class,
                lecture.lectureId,
                lecture.lectureName,
                lecture.lecturePlatform,
                lecture.lecturePath
            ))
            .from(lecture)
            .where(
                lecture.user.eq(user),
                StringUtils.isNotBlank(lectureName) ? lecture.lectureName.contains(lectureName) : null,
                lecturePlatform != null ? lecture.lecturePlatform.eq(lecturePlatform) : null
            )
            .fetch();
    }

    @Override
    public Lecture findFetchByLectureInfo(Long lectureId) {
        return queryFactory.select(lecture)
            .from(lecture)
            .join(lecture.lectureToCurriculumList, lectureToCurriculum).fetchJoin()
            .join(lectureToCurriculum.curriculum, curriculum).fetchJoin()
            .leftJoin(curriculum.lecturer, lecturer).fetchJoin()
            .where(lecture.lectureId.eq(lectureId))
            .fetchOne();
    }
}
