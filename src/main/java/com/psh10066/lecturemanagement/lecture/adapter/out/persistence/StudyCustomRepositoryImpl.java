package com.psh10066.lecturemanagement.lecture.adapter.out.persistence;

import com.psh10066.lecturemanagement.lecture.application.port.in.dto.StudyListDTO;
import com.psh10066.lecturemanagement.lecture.domain.LecturePlatform;
import com.psh10066.lecturemanagement.user.domain.User;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.psh10066.lecturemanagement.lecture.adapter.out.persistence.QCurriculumJpaEntity.curriculumJpaEntity;
import static com.psh10066.lecturemanagement.lecture.adapter.out.persistence.QLectureJpaEntity.lectureJpaEntity;
import static com.psh10066.lecturemanagement.lecture.adapter.out.persistence.QLectureToCurriculumJpaEntity.lectureToCurriculumJpaEntity;
import static com.psh10066.lecturemanagement.lecture.adapter.out.persistence.QLecturerJpaEntity.lecturerJpaEntity;
import static com.psh10066.lecturemanagement.lecture.adapter.out.persistence.QSectionJpaEntity.sectionJpaEntity;
import static com.psh10066.lecturemanagement.lecture.adapter.out.persistence.QStudyJpaEntity.studyJpaEntity;

@Repository
@RequiredArgsConstructor
public class StudyCustomRepositoryImpl implements StudyCustomRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<StudyListDTO> findAllStudy(User user, Pageable pageable, LecturePlatform lecturePlatform, Long lectureId, String lecturerName, String studyName) {
        List<StudyListDTO> fetch = queryFactory.select(Projections.constructor(StudyListDTO.class,
                lectureJpaEntity.lectureId,
                lectureJpaEntity.lectureName,
                lectureJpaEntity.lecturePlatform,
                lectureJpaEntity.lecturePath,
                lecturerJpaEntity.lecturerName,
                sectionJpaEntity.sectionName,
                studyJpaEntity.studyName
            ))
            .from(lectureJpaEntity)
            .join(lectureToCurriculumJpaEntity).on(lectureJpaEntity.lectureId.eq(lectureToCurriculumJpaEntity.lectureId))
            .join(curriculumJpaEntity).on(curriculumJpaEntity.curriculumId.eq(lectureToCurriculumJpaEntity.curriculumId))
            .leftJoin(lecturerJpaEntity).on(lecturerJpaEntity.lecturerId.eq(curriculumJpaEntity.lecturerId))
            .join(sectionJpaEntity).on(sectionJpaEntity.curriculumId.eq(curriculumJpaEntity.curriculumId))
            .join(studyJpaEntity).on(studyJpaEntity.sectionId.eq(sectionJpaEntity.sectionId))
            .where(
                lectureJpaEntity.userId.eq(user.getUserId()),
                lecturePlatform != null ? lectureJpaEntity.lecturePlatform.eq(lecturePlatform) : null,
                lectureId != null ? lectureJpaEntity.lectureId.eq(lectureId) : null,
                StringUtils.isNotBlank(lecturerName) ? lecturerJpaEntity.lecturerName.contains(lecturerName) : null,
                StringUtils.isNotBlank(studyName) ? studyJpaEntity.studyName.contains(studyName) : null
            )
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetch();

        JPAQuery<Long> count = queryFactory.select(lectureJpaEntity.count())
            .from(lectureJpaEntity)
            .join(lectureToCurriculumJpaEntity).on(lectureJpaEntity.lectureId.eq(lectureToCurriculumJpaEntity.lectureId))
            .join(curriculumJpaEntity).on(curriculumJpaEntity.curriculumId.eq(lectureToCurriculumJpaEntity.curriculumId))
            .leftJoin(lecturerJpaEntity).on(lecturerJpaEntity.lecturerId.eq(curriculumJpaEntity.lecturerId))
            .join(sectionJpaEntity).on(sectionJpaEntity.curriculumId.eq(curriculumJpaEntity.curriculumId))
            .join(studyJpaEntity).on(studyJpaEntity.sectionId.eq(sectionJpaEntity.sectionId))
            .where(
                lectureJpaEntity.userId.eq(user.getUserId()),
                lecturePlatform != null ? lectureJpaEntity.lecturePlatform.eq(lecturePlatform) : null,
                lectureId != null ? lectureJpaEntity.lectureId.eq(lectureId) : null,
                StringUtils.isNotBlank(lecturerName) ? lecturerJpaEntity.lecturerName.contains(lecturerName) : null,
                StringUtils.isNotBlank(studyName) ? studyJpaEntity.studyName.contains(studyName) : null
            );

        return PageableExecutionUtils.getPage(fetch, pageable, count::fetchOne);
    }
}
