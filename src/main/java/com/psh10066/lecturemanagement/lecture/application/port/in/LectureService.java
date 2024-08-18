package com.psh10066.lecturemanagement.lecture.application.port.in;

import com.psh10066.lecturemanagement.lecture.adapter.in.web.request.RegisterInflearnLectureRequest;
import com.psh10066.lecturemanagement.lecture.application.port.in.command.LecturesCommand;
import com.psh10066.lecturemanagement.lecture.application.port.in.command.ModifyLectureCommand;
import com.psh10066.lecturemanagement.lecture.application.port.in.command.RegisterLectureCommand;
import com.psh10066.lecturemanagement.lecture.application.port.in.dto.LectureInfoDTO;
import com.psh10066.lecturemanagement.lecture.application.port.in.dto.LectureModifyInfoDTO;
import com.psh10066.lecturemanagement.lecture.domain.Lecture;
import com.psh10066.lecturemanagement.user.domain.User;

import java.util.List;

public interface LectureService {

    List<Lecture> lectureList(User user);

    List<Lecture> lectureList(User user, LecturesCommand command);

    LectureInfoDTO lectureInfo(Long lectureId);

    LectureModifyInfoDTO lectureModifyInfo(Long lectureId);

    void modifyLecture(User user, ModifyLectureCommand command);

    Lecture registerLecture(User user, RegisterLectureCommand command);

    Lecture registerInflearnLecture(User user, RegisterInflearnLectureRequest request);
}
