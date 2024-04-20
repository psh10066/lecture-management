package com.psh10066.lecturemanagement.domain.study;

import com.psh10066.lecturemanagement.domain.user.User;
import com.psh10066.lecturemanagement.presentation.dto.StudyListDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StudyCustomRepository {

    Page<StudyListDTO> findAllStudy(User user, Pageable pageable, Long lectureId, String lecturerName, String studyName);
}
