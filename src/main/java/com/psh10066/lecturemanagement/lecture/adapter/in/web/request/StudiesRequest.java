package com.psh10066.lecturemanagement.lecture.adapter.in.web.request;

import com.psh10066.lecturemanagement.lecture.domain.LecturePlatform;

public record StudiesRequest(
    LecturePlatform lecturePlatform,
    Long lectureId,
    String lecturerName,
    String studyName
) {
}
