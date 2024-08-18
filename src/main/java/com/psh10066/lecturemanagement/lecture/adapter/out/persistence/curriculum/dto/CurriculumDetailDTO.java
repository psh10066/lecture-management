package com.psh10066.lecturemanagement.lecture.adapter.out.persistence.curriculum.dto;

public record CurriculumDetailDTO(
    Long curriculumId,
    String curriculumName,
    Long lecturerId,
    String lecturerName
) {
}
