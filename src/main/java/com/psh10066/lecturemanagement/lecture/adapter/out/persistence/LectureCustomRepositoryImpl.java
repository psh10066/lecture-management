package com.psh10066.lecturemanagement.lecture.adapter.out.persistence;

import com.psh10066.lecturemanagement.lecture.application.port.in.dto.LectureInfoDTO;
import com.psh10066.lecturemanagement.lecture.domain.Lecture;
import com.psh10066.lecturemanagement.lecture.domain.LecturePlatform;
import com.psh10066.lecturemanagement.user.domain.User;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.psh10066.lecturemanagement.lecture.adapter.out.persistence.QLectureJpaEntity.lectureJpaEntity;
import static com.psh10066.lecturemanagement.lecture.adapter.out.persistence.QLecturerJpaEntity.lecturerJpaEntity;
import static com.psh10066.lecturemanagement.lecture.domain.QCurriculum.curriculum;
import static com.psh10066.lecturemanagement.lecture.domain.QLectureToCurriculum.lectureToCurriculum;

@Repository
@RequiredArgsConstructor
public class LectureCustomRepositoryImpl implements LectureCustomRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Lecture> findAllLecture(User user, String lectureName, LecturePlatform lecturePlatform) {
        return queryFactory.select(Projections.constructor(Lecture.class,
                lectureJpaEntity.lectureId,
                lectureJpaEntity.lectureName,
                lectureJpaEntity.lecturePlatform,
                lectureJpaEntity.lecturePath,
                lectureJpaEntity.userId
            ))
            .from(lectureJpaEntity)
            .where(
                lectureJpaEntity.userId.eq(user.getUserId()),
                StringUtils.isNotBlank(lectureName) ? lectureJpaEntity.lectureName.contains(lectureName) : null,
                lecturePlatform != null ? lectureJpaEntity.lecturePlatform.eq(lecturePlatform) : null
            )
            .fetch();
    }

    @Override
    public LectureInfoDTO findFetchByLectureInfo(Long lectureId) {
        LectureJpaEntity result = queryFactory.select(lectureJpaEntity)
            .from(lectureJpaEntity)
            .join(lectureJpaEntity.lectureToCurriculumList, lectureToCurriculum).fetchJoin()
            .join(lectureToCurriculum.curriculum, curriculum).fetchJoin()
            .leftJoin(curriculum.lecturerJpaEntity, lecturerJpaEntity).fetchJoin()
            .where(lectureJpaEntity.lectureId.eq(lectureId))
            .fetchOne();
        return result != null ? LectureMapper.INSTANCE.toLectureInfo(result) : null;
    }
}
