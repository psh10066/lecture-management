package com.psh10066.lecturemanagement.lecture.application.port.in.dto;

import com.psh10066.lecturemanagement.lecture.domain.LecturePlatform;

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
