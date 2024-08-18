package com.psh10066.lecturemanagement.lecture.application.port.in.command;

import com.psh10066.lecturemanagement.lecture.domain.LecturePlatform;

import java.time.LocalTime;
import java.util.List;

public record RegisterLectureCommand(
    String lectureName,
    LecturePlatform lecturePlatform,
    String lecturePath,
    List<CurriculumDTO> curriculumList
) {
    public record CurriculumDTO(
        String curriculumName,
        String lecturerName,
        List<SectionDTO> sectionList
    ) {
    }

    public record SectionDTO(
        String sectionName,
        List<StudyDTO> studyList
    ) {
    }

    public record StudyDTO(
        String studyName,
        LocalTime studyTime
    ) {
    }
}
