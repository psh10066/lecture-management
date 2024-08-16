package com.psh10066.lecturemanagement.lecture.adapter.out.persistence;

import com.psh10066.lecturemanagement.lecture.application.port.in.dto.LectureInfoDTO;
import com.psh10066.lecturemanagement.lecture.domain.Lecture;
import com.psh10066.lecturemanagement.lecture.domain.LecturePlatform;
import com.psh10066.lecturemanagement.user.domain.User;

import java.util.List;

public interface LectureCustomRepository {

    List<Lecture> findAllLecture(User user, String lectureName, LecturePlatform lecturePlatform);

    LectureInfoDTO findFetchByLectureInfo(Long lectureId);
}
