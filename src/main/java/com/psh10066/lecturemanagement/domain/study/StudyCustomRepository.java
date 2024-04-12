package com.psh10066.lecturemanagement.domain.study;

import com.psh10066.lecturemanagement.presentation.dto.StudyListDTO;

import java.util.List;

public interface StudyCustomRepository {

    List<StudyListDTO> findAllStudy(Long lectureId, String lecturerName, String studyName);
}
