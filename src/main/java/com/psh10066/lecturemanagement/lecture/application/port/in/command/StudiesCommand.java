package com.psh10066.lecturemanagement.lecture.application.port.in.command;

import com.psh10066.lecturemanagement.lecture.domain.LecturePlatform;

public record StudiesCommand(
    LecturePlatform lecturePlatform,
    Long lectureId,
    String lecturerName,
    String studyName
) {
}
