package com.psh10066.lecturemanagement.lecture.adapter.in.web.dto;

import com.psh10066.lecturemanagement.lecture.domain.Lecture;
import com.psh10066.lecturemanagement.lecture.domain.LecturePlatform;

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
