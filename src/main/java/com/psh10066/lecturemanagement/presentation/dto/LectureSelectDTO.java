package com.psh10066.lecturemanagement.presentation.dto;

import com.psh10066.lecturemanagement.domain.lecture.Lecture;

public record LectureSelectDTO(
    Long lectureId,
    String lectureName
) {
    public static LectureSelectDTO from(Lecture lecture) {
        return new LectureSelectDTO(lecture.getLectureId(), lecture.getLectureName());
    }
}
