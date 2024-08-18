package com.psh10066.lecturemanagement.lecture.adapter.out.persistence.curriculum;

import com.psh10066.lecturemanagement.lecture.domain.Curriculum;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.psh10066.lecturemanagement.lecture.adapter.out.persistence.curriculum.QCurriculumJpaEntity.curriculumJpaEntity;
import static com.psh10066.lecturemanagement.lecture.adapter.out.persistence.lecturetocurriculum.QLectureToCurriculumJpaEntity.lectureToCurriculumJpaEntity;

@RequiredArgsConstructor
public class CurriculumCustomRepositoryImpl implements CurriculumCustomRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Curriculum> findAllByLectureId(Long lectureId) {
        return queryFactory.select(Projections.constructor(Curriculum.class,
                curriculumJpaEntity.curriculumId,
                curriculumJpaEntity.curriculumName,
                curriculumJpaEntity.lecturerId
            ))
            .from(lectureToCurriculumJpaEntity)
            .join(curriculumJpaEntity).on(curriculumJpaEntity.curriculumId.eq(lectureToCurriculumJpaEntity.curriculumId))
            .where(lectureToCurriculumJpaEntity.lectureId.eq(lectureId))
            .fetch();
    }
}
