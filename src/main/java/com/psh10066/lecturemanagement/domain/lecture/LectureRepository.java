package com.psh10066.lecturemanagement.domain.lecture;

import com.psh10066.lecturemanagement.domain.lecture.type.LecturePlatform;
import com.psh10066.lecturemanagement.domain.user.User;
import com.psh10066.lecturemanagement.presentation.dto.LectureListDTO;

import java.util.List;

public interface LectureRepository {

    List<Lecture> findAllByUser(User user);

    Lecture save(Lecture lecture);

    List<LectureListDTO> findAllLecture(User user, String s, LecturePlatform lecturePlatform);

    Lecture findFetchByLectureInfo(Long lectureId);
}
