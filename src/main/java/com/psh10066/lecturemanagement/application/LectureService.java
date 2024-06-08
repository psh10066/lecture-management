package com.psh10066.lecturemanagement.application;

import com.psh10066.lecturemanagement.domain.lecture.Lecture;
import com.psh10066.lecturemanagement.presentation.dto.*;
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
