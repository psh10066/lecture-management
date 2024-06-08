package com.psh10066.lecturemanagement.application;

import com.psh10066.lecturemanagement.domain.study.StudyRepository;
import com.psh10066.lecturemanagement.domain.user.User;
import com.psh10066.lecturemanagement.presentation.dto.StudiesRequest;
import com.psh10066.lecturemanagement.presentation.dto.StudyListDTO;
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
