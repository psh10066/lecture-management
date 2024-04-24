package com.psh10066.lecturemanagement.presentation.dto;

import com.psh10066.lecturemanagement.domain.lecture.type.LecturePlatform;

import java.time.LocalTime;
import java.util.List;

public record LectureInfoDTO(
    Long lectureId,
    String lectureName,
    LecturePlatform lecturePlatform,
    String lecturePath,
    List<CurriculumDTO> curriculums
) {
    public record CurriculumDTO(
        Long curriculumId,
        String curriculumName,
        String lecturerName,
        List<SectionDTO> sections
    ) {
    }

    public record SectionDTO(
        Long sectionId,
        String sectionName,
        List<StudyDTO> studies
    ) {
    }

    public record StudyDTO(
        Long studyId,
        String studyName,
        LocalTime studyTime
    ) {
    }
}
