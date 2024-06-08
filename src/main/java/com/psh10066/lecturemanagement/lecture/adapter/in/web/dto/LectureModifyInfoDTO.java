package com.psh10066.lecturemanagement.lecture.adapter.in.web.dto;

import com.psh10066.lecturemanagement.lecture.domain.LecturePlatform;

import java.util.List;

public record LectureModifyInfoDTO(
    Long lectureId,
    String lectureName,
    LecturePlatform lecturePlatform,
    String lecturePath,
    List<CurriculumDTO> curriculums
) {
    public record CurriculumDTO(
        Long curriculumId,
        String curriculumName,
        String lecturerName
    ) {
    }
}
