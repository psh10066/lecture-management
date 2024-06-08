package com.psh10066.lecturemanagement.application;

import com.psh10066.lecturemanagement.domain.user.User;
import com.psh10066.lecturemanagement.presentation.dto.StudiesRequest;
import com.psh10066.lecturemanagement.presentation.dto.StudyListDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StudyService {

    Page<StudyListDTO> studyList(User user, Pageable pageable, StudiesRequest request);
}
