package com.psh10066.lecturemanagement.presentation.dto;

import com.psh10066.lecturemanagement.domain.lecturer.Lecturer;

public record LecturerListDTO(
    Long lecturerId,
    String lecturerName
) {
    public static LecturerListDTO from(Lecturer lecturer) {
        return new LecturerListDTO(lecturer.getLecturerId(), lecturer.getLecturerName());
    }
}
