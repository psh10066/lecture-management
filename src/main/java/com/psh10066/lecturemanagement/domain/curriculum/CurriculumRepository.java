package com.psh10066.lecturemanagement.domain.curriculum;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CurriculumRepository extends JpaRepository<Curriculum, Long>, CurriculumCustomRepository {
}
