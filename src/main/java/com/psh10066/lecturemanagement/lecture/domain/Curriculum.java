package com.psh10066.lecturemanagement.lecture.domain;

public record Curriculum(
    Long curriculumId,
    String curriculumName,
    Long lecturerId
) {

    public static Curriculum createCurriculum(String curriculumName, Long lecturerId) {
        return new Curriculum(null, curriculumName, lecturerId);
    }

    public Curriculum updateCurriculum(Long lecturerId) {
        return new Curriculum(curriculumId, curriculumName, lecturerId);
    }
}
