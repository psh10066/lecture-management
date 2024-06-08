package com.psh10066.lecturemanagement.lecture.application.port.out;

import com.psh10066.lecturemanagement.lecture.domain.Section;

public interface SectionRepository {

    Section save(Section section);
}
