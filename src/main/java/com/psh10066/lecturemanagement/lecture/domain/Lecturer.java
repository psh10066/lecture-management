package com.psh10066.lecturemanagement.lecture.domain;

public record Lecturer(
    Long lecturerId,
    String lecturerName,
    Long userId
) {

    public static Lecturer createLecturer(String lecturerName, Long userId) {
        return new Lecturer(null, lecturerName, userId);
    }
}
