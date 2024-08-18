package com.psh10066.lecturemanagement.lecture.application.port.out;

import com.psh10066.lecturemanagement.lecture.application.port.in.command.RegisterLectureCommand;
import com.psh10066.lecturemanagement.lecture.application.port.in.dto.LectureInfoDTO;

import java.util.List;

public interface SectionRepository {

    void register(Long curriculumId, RegisterLectureCommand.SectionDTO sectionDTO);

    List<LectureInfoDTO.SectionDTO> findInfoByCurriculumIdIn(List<Long> curriculumIds);
}
