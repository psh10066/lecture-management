package com.psh10066.lecturemanagement.presentation.dto;

import com.psh10066.lecturemanagement.domain.lecture.type.LecturePlatform;

public record StudyListDTO(
    Long lectureId,
    String lectureName,
    LecturePlatform lecturePlatform,
    String lecturePath,
    String lecturerName,
    String sectionName,
    String studyName
) {
}
