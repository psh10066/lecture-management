package com.psh10066.lecturemanagement.lecture.application.port.in.command;

import java.util.List;

public record ModifyLectureCommand(
    Long lectureId,
    List<InnerDTO> curriculumList
) {
    public record InnerDTO(
        Long curriculumId,
        String lecturerName
    ) {
    }
}
