package com.psh10066.lecturemanagement.lecture.adapter.out.persistence.curriculum;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CurriculumJpaRepository extends JpaRepository<CurriculumJpaEntity, Long>, CurriculumCustomRepository {
}
