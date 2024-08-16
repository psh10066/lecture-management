package com.psh10066.lecturemanagement.lecture.domain;

public record Lecture(
    Long lectureId,
    String lectureName,
    LecturePlatform lecturePlatform,
    String lecturePath,
    Long userId
) {

    public static Lecture createLecture(String lectureName, LecturePlatform lecturePlatform, String lecturePath, Long userId) {
        return new Lecture(null, lectureName, lecturePlatform, lecturePath, userId);
    }
}
