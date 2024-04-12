package com.psh10066.lecturemanagement.presentation.dto;

public record CurriculumsRequest(
    Long lectureId,
    String lecturerName,
    String curriculumName
) {
}
