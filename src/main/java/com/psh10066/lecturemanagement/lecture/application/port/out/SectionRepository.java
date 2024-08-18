package com.psh10066.lecturemanagement.lecture.application.port.out;

import com.psh10066.lecturemanagement.lecture.application.port.in.command.RegisterLectureCommand;

public interface SectionRepository {

    void register(Long curriculumId, RegisterLectureCommand.SectionDTO sectionDTO);
}
