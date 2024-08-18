package com.psh10066.lecturemanagement.lecture.application.port.out;

import com.psh10066.lecturemanagement.lecture.application.port.in.command.ModifyLectureCommand;
import com.psh10066.lecturemanagement.lecture.application.port.in.command.RegisterLectureCommand;
import com.psh10066.lecturemanagement.lecture.domain.Curriculum;
import com.psh10066.lecturemanagement.user.domain.User;

public interface CurriculumRepository {

    Curriculum save(Curriculum curriculum);

    void updateLecturer(ModifyLectureCommand command, User user);

    Curriculum register(User user, Long lectureId, RegisterLectureCommand.CurriculumDTO curriculumDTO);
}
