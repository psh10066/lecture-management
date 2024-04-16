package com.psh10066.lecturemanagement.domain.curriculum;

import com.psh10066.lecturemanagement.presentation.dto.CurriculumInfoDTO;
import com.psh10066.lecturemanagement.presentation.dto.CurriculumListDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CurriculumCustomRepository {

    Page<CurriculumListDTO> findAllCurriculum(Pageable pageable, Long lectureId, String lecturerName, String curriculumName);

    CurriculumInfoDTO findCurriculum(Long curriculumId);

}
