package com.psh10066.lecturemanagement.lecture.application.port.in;

import com.psh10066.lecturemanagement.lecture.application.port.in.dto.StudyListDTO;
import com.psh10066.lecturemanagement.lecture.adapter.in.web.request.StudiesRequest;
import com.psh10066.lecturemanagement.user.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StudyService {

    Page<StudyListDTO> studyList(User user, Pageable pageable, StudiesRequest request);
}
