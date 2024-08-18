package com.psh10066.lecturemanagement.lecture.application.service;

import com.psh10066.lecturemanagement.lecture.application.port.in.command.StudiesCommand;
import com.psh10066.lecturemanagement.lecture.application.port.in.dto.StudyListDTO;
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

    public Page<StudyListDTO> studyList(User user, Pageable pageable, StudiesCommand command) {
        return studyRepository.findAllStudy(user, pageable, command.lecturePlatform(), command.lectureId(), command.lecturerName(), command.studyName());
    }
}
