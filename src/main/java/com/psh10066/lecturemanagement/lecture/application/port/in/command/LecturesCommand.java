package com.psh10066.lecturemanagement.lecture.application.port.in.command;

import com.psh10066.lecturemanagement.lecture.domain.LecturePlatform;

public record LecturesCommand(
    String lectureName,
    LecturePlatform lecturePlatform
) {
}
