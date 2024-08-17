package com.psh10066.lecturemanagement.lecture.adapter.out.persistence.lecture;

import com.psh10066.lecturemanagement.lecture.domain.Lecture;
import com.psh10066.lecturemanagement.lecture.domain.LecturePlatform;
import com.psh10066.lecturemanagement.user.domain.User;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.psh10066.lecturemanagement.lecture.adapter.out.persistence.lecture.QLectureJpaEntity.lectureJpaEntity;

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
}
