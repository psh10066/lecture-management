package com.psh10066.lecturemanagement.lecture.adapter.out.persistence.curriculum;

import com.psh10066.lecturemanagement.lecture.adapter.out.persistence.curriculum.dto.CurriculumDetailDTO;

import java.util.List;

public interface CurriculumCustomRepository {

    List<CurriculumDetailDTO> findAllByLectureId(Long lectureId);
}
