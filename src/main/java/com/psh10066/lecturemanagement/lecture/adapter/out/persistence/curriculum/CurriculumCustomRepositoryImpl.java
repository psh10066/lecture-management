package com.psh10066.lecturemanagement.lecture.adapter.out.persistence.curriculum;

import com.psh10066.lecturemanagement.lecture.adapter.out.persistence.curriculum.dto.CurriculumDetailDTO;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.psh10066.lecturemanagement.lecture.adapter.out.persistence.curriculum.QCurriculumJpaEntity.curriculumJpaEntity;
import static com.psh10066.lecturemanagement.lecture.adapter.out.persistence.lecturer.QLecturerJpaEntity.lecturerJpaEntity;
import static com.psh10066.lecturemanagement.lecture.adapter.out.persistence.lecturetocurriculum.QLectureToCurriculumJpaEntity.lectureToCurriculumJpaEntity;

@RequiredArgsConstructor
public class CurriculumCustomRepositoryImpl implements CurriculumCustomRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<CurriculumDetailDTO> findAllByLectureId(Long lectureId) {
        return queryFactory.select(Projections.constructor(CurriculumDetailDTO.class,
                curriculumJpaEntity.curriculumId,
                curriculumJpaEntity.curriculumName,
                curriculumJpaEntity.lecturerId,
                lecturerJpaEntity.lecturerName
            ))
            .from(lectureToCurriculumJpaEntity)
            .join(curriculumJpaEntity).on(curriculumJpaEntity.curriculumId.eq(lectureToCurriculumJpaEntity.curriculumId))
            .leftJoin(lecturerJpaEntity).on(lecturerJpaEntity.lecturerId.eq(curriculumJpaEntity.lecturerId))
            .where(lectureToCurriculumJpaEntity.lectureId.eq(lectureId))
            .fetch();
    }
}
