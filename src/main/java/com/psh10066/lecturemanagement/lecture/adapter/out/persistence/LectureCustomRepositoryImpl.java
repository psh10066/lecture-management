package com.psh10066.lecturemanagement.lecture.adapter.out.persistence;

import com.psh10066.lecturemanagement.lecture.adapter.in.web.dto.LectureListDTO;
import com.psh10066.lecturemanagement.lecture.domain.Lecture;
import com.psh10066.lecturemanagement.lecture.domain.LecturePlatform;
import com.psh10066.lecturemanagement.user.adapter.out.persistence.UserJpaEntity;
import com.psh10066.lecturemanagement.user.domain.User;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.psh10066.lecturemanagement.lecture.domain.QCurriculum.curriculum;
import static com.psh10066.lecturemanagement.lecture.domain.QLecture.lecture;
import static com.psh10066.lecturemanagement.lecture.domain.QLectureToCurriculum.lectureToCurriculum;
import static com.psh10066.lecturemanagement.lecture.domain.QLecturer.lecturer;

@Repository
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
                lecture.user.eq(UserJpaEntity.from(user)),
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
