package com.psh10066.lecturemanagement.domain.lecture;

import com.psh10066.lecturemanagement.domain.lecture.type.LecturePlatform;
import com.psh10066.lecturemanagement.presentation.dto.LectureListDTO;
import com.psh10066.lecturemanagement.user.domain.User;

import java.util.List;

public interface LectureCustomRepository {

    List<LectureListDTO> findAllLecture(User user, String lectureName, LecturePlatform lecturePlatform);

    Lecture findFetchByLectureInfo(Long lectureId);
}
