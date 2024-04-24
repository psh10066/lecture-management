package com.psh10066.lecturemanagement.presentation.dto;

public record CurriculumListDTO(
    Long lectureId,
    String lectureName,
    String lecturePath,
    String lecturerName,
    Long curriculumId,
    String curriculumName
) {
}
