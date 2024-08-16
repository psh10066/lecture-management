package com.psh10066.lecturemanagement.lecture.application.port.out;

import com.psh10066.lecturemanagement.lecture.application.port.in.dto.LectureInfoDTO;
import com.psh10066.lecturemanagement.lecture.domain.Lecture;
import com.psh10066.lecturemanagement.lecture.domain.LecturePlatform;
import com.psh10066.lecturemanagement.user.domain.User;

import java.util.List;

public interface LectureRepository {

    List<Lecture> findAllByUser(User user);

    Lecture save(Lecture lecture);

    List<Lecture> findAllLecture(User user, String s, LecturePlatform lecturePlatform);

    LectureInfoDTO findFetchByLectureInfo(Long lectureId);
}
