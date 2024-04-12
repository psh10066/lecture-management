package com.psh10066.lecturemanagement.domain.curriculum;

import com.psh10066.lecturemanagement.presentation.dto.CurriculumInfoDTO;
import com.psh10066.lecturemanagement.presentation.dto.CurriculumListDTO;

import java.util.List;

public interface CurriculumCustomRepository {

    List<CurriculumListDTO> findAllCurriculum(Long lectureId, String lecturerName, String curriculumName);

    CurriculumInfoDTO findCurriculum(Long curriculumId);

}
