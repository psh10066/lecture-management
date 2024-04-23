package com.psh10066.lecturemanagement.presentation.dto;

import com.psh10066.lecturemanagement.domain.lecture.type.LecturePlatform;

public record LecturesRequest(
    String lectureName,
    LecturePlatform lecturePlatform
) {
}
