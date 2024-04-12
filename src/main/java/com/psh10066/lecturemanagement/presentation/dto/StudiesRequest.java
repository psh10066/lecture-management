package com.psh10066.lecturemanagement.presentation.dto;

public record StudiesRequest(
    Long lectureId,
    String lecturerName,
    String studyName
) {
}
