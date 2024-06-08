package com.psh10066.lecturemanagement.lecture.application.port.out;

import com.psh10066.lecturemanagement.lecture.adapter.in.web.dto.LectureListDTO;
import com.psh10066.lecturemanagement.lecture.domain.Lecture;
import com.psh10066.lecturemanagement.lecture.domain.LecturePlatform;
import com.psh10066.lecturemanagement.user.domain.User;

import java.util.List;

public interface LectureRepository {

    List<Lecture> findAllByUser(User user);

    Lecture save(Lecture lecture);

    List<LectureListDTO> findAllLecture(User user, String s, LecturePlatform lecturePlatform);

    Lecture findFetchByLectureInfo(Long lectureId);
}
