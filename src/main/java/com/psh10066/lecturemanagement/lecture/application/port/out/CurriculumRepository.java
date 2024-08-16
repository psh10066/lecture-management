package com.psh10066.lecturemanagement.lecture.application.port.out;

import com.psh10066.lecturemanagement.lecture.adapter.in.web.request.ModifyLectureRequest;
import com.psh10066.lecturemanagement.lecture.domain.Curriculum;
import com.psh10066.lecturemanagement.user.domain.User;

import java.util.List;

public interface CurriculumRepository {

    Curriculum save(Curriculum curriculum);

    void updateLecturer(List<ModifyLectureRequest.InnerDTO> command, User user);
}
