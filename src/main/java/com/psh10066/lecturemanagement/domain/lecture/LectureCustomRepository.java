package com.psh10066.lecturemanagement.domain.lecture;

import com.psh10066.lecturemanagement.domain.lecture.type.LecturePlatform;
import com.psh10066.lecturemanagement.domain.user.User;
import com.psh10066.lecturemanagement.presentation.dto.LectureListDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LectureCustomRepository {

    Page<LectureListDTO> findAllLecture(User user, Pageable pageable, String lectureName, LecturePlatform lecturePlatform);

    Lecture findFetchByLectureInfo(Long lectureId);
}
