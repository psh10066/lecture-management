package com.psh10066.lecturemanagement.presentation.dto;

public record CurriculumInfoDTO(
    String lectureName,
    Long lecturerId,
    String lecturerName,
    Long curriculumId,
    String curriculumName
) {
}
