package com.psh10066.lecturemanagement.lecture.adapter.out.persistence.study;

import com.psh10066.lecturemanagement.lecture.application.port.in.dto.StudyListDTO;
import com.psh10066.lecturemanagement.lecture.domain.LecturePlatform;
import com.psh10066.lecturemanagement.user.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StudyCustomRepository {

    Page<StudyListDTO> findAllStudy(User user, Pageable pageable, LecturePlatform lecturePlatform, Long lectureId, String lecturerName, String studyName);
}
