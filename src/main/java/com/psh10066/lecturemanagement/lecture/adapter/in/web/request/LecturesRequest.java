package com.psh10066.lecturemanagement.lecture.adapter.in.web.request;

import com.psh10066.lecturemanagement.lecture.domain.LecturePlatform;

public record LecturesRequest(
    String lectureName,
    LecturePlatform lecturePlatform
) {
}
