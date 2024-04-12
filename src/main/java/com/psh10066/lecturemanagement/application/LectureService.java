package com.psh10066.lecturemanagement.application;

import com.psh10066.lecturemanagement.domain.lecture.LectureRepository;
import com.psh10066.lecturemanagement.presentation.dto.LectureSelectDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LectureService {

    private final LectureRepository lectureRepository;

    public List<LectureSelectDTO> lectureList() {
        return lectureRepository.findAll().stream()
            .map(LectureSelectDTO::from)
            .toList();
    }
}
