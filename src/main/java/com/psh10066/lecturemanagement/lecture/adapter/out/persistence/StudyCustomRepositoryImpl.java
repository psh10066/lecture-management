package com.psh10066.lecturemanagement.lecture.adapter.out.persistence;

import com.psh10066.lecturemanagement.lecture.adapter.in.web.dto.StudyListDTO;
import com.psh10066.lecturemanagement.lecture.domain.LecturePlatform;
import com.psh10066.lecturemanagement.user.adapter.out.persistence.UserJpaEntity;
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

import static com.psh10066.lecturemanagement.lecture.domain.QCurriculum.curriculum;
import static com.psh10066.lecturemanagement.lecture.domain.QLecture.lecture;
import static com.psh10066.lecturemanagement.lecture.domain.QLectureToCurriculum.lectureToCurriculum;
import static com.psh10066.lecturemanagement.lecture.domain.QLecturer.lecturer;
import static com.psh10066.lecturemanagement.lecture.domain.QSection.section;
import static com.psh10066.lecturemanagement.lecture.domain.QStudy.study;

@Repository
@RequiredArgsConstructor
public class StudyCustomRepositoryImpl implements StudyCustomRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<StudyListDTO> findAllStudy(User user, Pageable pageable, LecturePlatform lecturePlatform, Long lectureId, String lecturerName, String studyName) {
        UserJpaEntity userJpaEntity = UserJpaEntity.from(user);
        List<StudyListDTO> fetch = queryFactory.select(Projections.constructor(StudyListDTO.class,
                lecture.lectureId,
                lecture.lectureName,
                lecture.lecturePlatform,
                lecture.lecturePath,
                lecturer.lecturerName,
                section.sectionName,
                study.studyName
            ))
            .from(lecture)
            .join(lectureToCurriculum).on(lecture.lectureId.eq(lectureToCurriculum.lecture.lectureId))
            .join(curriculum).on(curriculum.curriculumId.eq(lectureToCurriculum.curriculum.curriculumId))
            .leftJoin(lecturer).on(lecturer.lecturerId.eq(curriculum.lecturer.lecturerId))
            .join(section).on(section.curriculum.curriculumId.eq(curriculum.curriculumId))
            .join(study).on(study.section.sectionId.eq(section.sectionId))
            .where(
                lecture.user.eq(userJpaEntity),
                lecturePlatform != null ? lecture.lecturePlatform.eq(lecturePlatform) : null,
                lectureId != null ? lecture.lectureId.eq(lectureId) : null,
                StringUtils.isNotBlank(lecturerName) ? lecturer.lecturerName.contains(lecturerName) : null,
                StringUtils.isNotBlank(studyName) ? study.studyName.contains(studyName) : null
            )
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetch();

        JPAQuery<Long> count = queryFactory.select(lecture.count())
            .from(lecture)
            .join(lectureToCurriculum).on(lecture.lectureId.eq(lectureToCurriculum.lecture.lectureId))
            .join(curriculum).on(curriculum.curriculumId.eq(lectureToCurriculum.curriculum.curriculumId))
            .leftJoin(lecturer).on(lecturer.lecturerId.eq(curriculum.lecturer.lecturerId))
            .join(section).on(section.curriculum.curriculumId.eq(curriculum.curriculumId))
            .join(study).on(study.section.sectionId.eq(section.sectionId))
            .where(
                lecture.user.eq(userJpaEntity),
                lecturePlatform != null ? lecture.lecturePlatform.eq(lecturePlatform) : null,
                lectureId != null ? lecture.lectureId.eq(lectureId) : null,
                StringUtils.isNotBlank(lecturerName) ? lecturer.lecturerName.contains(lecturerName) : null,
                StringUtils.isNotBlank(studyName) ? study.studyName.contains(studyName) : null
            );

        return PageableExecutionUtils.getPage(fetch, pageable, count::fetchOne);
    }
}
