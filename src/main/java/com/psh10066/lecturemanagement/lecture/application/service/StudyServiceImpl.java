package com.psh10066.lecturemanagement.lecture.application.service;

import com.psh10066.lecturemanagement.lecture.adapter.in.web.dto.StudyListDTO;
import com.psh10066.lecturemanagement.lecture.adapter.in.web.request.StudiesRequest;
import com.psh10066.lecturemanagement.lecture.application.port.in.StudyService;
import com.psh10066.lecturemanagement.lecture.application.port.out.StudyRepository;
import com.psh10066.lecturemanagement.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudyServiceImpl implements StudyService {

    private final StudyRepository studyRepository;

    public Page<StudyListDTO> studyList(User user, Pageable pageable, StudiesRequest request) {
        return studyRepository.findAllStudy(user, pageable, request.lecturePlatform(), request.lectureId(), request.lecturerName(), request.studyName());
    }
}
