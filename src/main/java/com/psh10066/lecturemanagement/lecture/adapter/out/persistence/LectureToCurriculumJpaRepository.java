package com.psh10066.lecturemanagement.lecture.adapter.out.persistence;

import com.psh10066.lecturemanagement.lecture.domain.LectureToCurriculum;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LectureToCurriculumJpaRepository extends JpaRepository<LectureToCurriculum, Long> {
}
