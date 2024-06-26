package com.psh10066.lecturemanagement.lecture.application.port.in;

import com.psh10066.lecturemanagement.lecture.adapter.in.web.dto.LectureInfoDTO;
import com.psh10066.lecturemanagement.lecture.adapter.in.web.dto.LectureListDTO;
import com.psh10066.lecturemanagement.lecture.adapter.in.web.dto.LectureModifyInfoDTO;
import com.psh10066.lecturemanagement.lecture.adapter.in.web.request.LecturesRequest;
import com.psh10066.lecturemanagement.lecture.adapter.in.web.request.ModifyLectureRequest;
import com.psh10066.lecturemanagement.lecture.adapter.in.web.request.RegisterFastcampusLectureRequest;
import com.psh10066.lecturemanagement.lecture.adapter.in.web.request.RegisterInflearnLectureRequest;
import com.psh10066.lecturemanagement.lecture.domain.Lecture;
import com.psh10066.lecturemanagement.user.domain.User;

import java.util.List;

public interface LectureService {

    List<LectureListDTO> lectureList(User user);

    List<LectureListDTO> lectureList(User user, LecturesRequest request);

    LectureInfoDTO lectureInfo(Long lectureId);

    LectureModifyInfoDTO lectureModifyInfo(Long lectureId);

    void modifyLecture(User user, Long lectureId, ModifyLectureRequest request);

    Lecture registerFastcampusLecture(User user, RegisterFastcampusLectureRequest request);

    Lecture registerInflearnLecture(User user, RegisterInflearnLectureRequest request);
}
