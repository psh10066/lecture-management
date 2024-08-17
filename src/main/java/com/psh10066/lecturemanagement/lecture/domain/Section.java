package com.psh10066.lecturemanagement.lecture.domain;

public record Section(
    Long sectionId,
    String sectionName,
    Long curriculumId
) {

    public static Section createSection(String sectionName, Long curriculumId) {
        return new Section(null, sectionName, curriculumId);
    }
}
