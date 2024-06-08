package com.psh10066.lecturemanagement.lecture.adapter.out.persistence;

import com.psh10066.lecturemanagement.lecture.domain.Curriculum;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurriculumJpaRepository extends JpaRepository<Curriculum, Long> {
}
