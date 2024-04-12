package com.psh10066.lecturemanagement.application;

import com.psh10066.lecturemanagement.domain.study.StudyRepository;
import com.psh10066.lecturemanagement.presentation.dto.StudyListDTO;
import com.psh10066.lecturemanagement.presentation.dto.StudiesRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudyService {

    private final StudyRepository studyRepository;

    public List<StudyListDTO> studyList(StudiesRequest request) {
        return studyRepository.findAllStudy(request.lectureId(), request.lecturerName(), request.studyName());
    }
}
