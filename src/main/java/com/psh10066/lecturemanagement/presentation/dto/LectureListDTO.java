package com.psh10066.lecturemanagement.presentation.dto;

import com.psh10066.lecturemanagement.domain.lecture.Lecture;
import com.psh10066.lecturemanagement.domain.lecture.type.LecturePlatform;

public record LectureListDTO(
    Long lectureId,
    String lectureName,
    LecturePlatform lecturePlatform,
    String lecturePath
) {
    public static LectureListDTO from(Lecture lecture) {
        return new LectureListDTO(lecture.getLectureId(), lecture.getLectureName(), lecture.getLecturePlatform(), lecture.getLecturePath());
    }
}
