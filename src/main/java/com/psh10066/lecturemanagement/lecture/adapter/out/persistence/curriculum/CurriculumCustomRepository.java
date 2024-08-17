package com.psh10066.lecturemanagement.lecture.adapter.out.persistence.curriculum;

import com.psh10066.lecturemanagement.lecture.domain.Curriculum;

import java.util.List;

public interface CurriculumCustomRepository {

    List<Curriculum> findAllByLectureId(Long lectureId);
}
