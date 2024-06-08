package com.psh10066.lecturemanagement.lecture.application.port.out;

import com.psh10066.lecturemanagement.lecture.domain.Curriculum;

public interface CurriculumRepository {

    Curriculum save(Curriculum curriculum);
}
