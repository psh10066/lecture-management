package com.psh10066.lecturemanagement.domain.study;

import com.psh10066.lecturemanagement.presentation.dto.StudyListDTO;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.psh10066.lecturemanagement.domain.curriculum.QCurriculum.curriculum;
import static com.psh10066.lecturemanagement.domain.lecture.QLecture.lecture;
import static com.psh10066.lecturemanagement.domain.lecturer.QLecturer.lecturer;
import static com.psh10066.lecturemanagement.domain.lecturetocurriculum.QLectureToCurriculum.lectureToCurriculum;
import static com.psh10066.lecturemanagement.domain.section.QSection.section;
import static com.psh10066.lecturemanagement.domain.study.QStudy.study;

@RequiredArgsConstructor
public class StudyCustomRepositoryImpl implements StudyCustomRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<StudyListDTO> findAllStudy(Long lectureId, String lecturerName, String studyName) {
        return queryFactory.select(Projections.constructor(StudyListDTO.class,
                lecture.lectureName,
                lecture.lecturePath,
                lecturer.lecturerName,
                section.sectionName,
                study.studyName
            ))
            .from(lecture)
            .join(lectureToCurriculum).on(lecture.lectureId.eq(lectureToCurriculum.lecture.lectureId))
            .join(curriculum).on(curriculum.curriculumId.eq(lectureToCurriculum.curriculum.curriculumId))
            .join(lecturer).on(lecturer.lecturerId.eq(curriculum.lecturer.lecturerId))
            .join(section).on(section.curriculum.curriculumId.eq(curriculum.curriculumId))
            .join(study).on(study.section.sectionId.eq(section.sectionId))
            .where(
                lectureId != null ? lecture.lectureId.eq(lectureId) : null,
                StringUtils.isNotBlank(lecturerName) ? lecturer.lecturerName.contains(lecturerName) : null,
                StringUtils.isNotBlank(studyName) ? study.studyName.contains(studyName) : null
            )
            .fetch();
    }
}
