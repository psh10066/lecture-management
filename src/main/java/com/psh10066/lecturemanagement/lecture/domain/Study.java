package com.psh10066.lecturemanagement.lecture.domain;

import java.time.LocalTime;

public record Study(
    Long studyId,
    String studyName,
    LocalTime studyTime,
    Long sectionId
) {

    public static Study createStudy(String studyName, LocalTime studyTime, Long sectionId) {
        return new Study(null, studyName, studyTime, sectionId);
    }
}
