package com.psh10066.lecturemanagement.lecture.domain;

public record LectureToCurriculum(
    Long lectureToCurriculumId,
    Long lectureId,
    Long curriculumId
) {

    public static LectureToCurriculum createLectureToCurriculum(Long lectureId, Long curriculumId) {
        return new LectureToCurriculum(null, lectureId, curriculumId);
    }
}
