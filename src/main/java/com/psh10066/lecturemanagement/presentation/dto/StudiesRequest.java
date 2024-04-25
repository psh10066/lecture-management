package com.psh10066.lecturemanagement.presentation.dto;

import com.psh10066.lecturemanagement.domain.lecture.type.LecturePlatform;

public record StudiesRequest(
    LecturePlatform lecturePlatform,
    Long lectureId,
    String lecturerName,
    String studyName
) {
}
