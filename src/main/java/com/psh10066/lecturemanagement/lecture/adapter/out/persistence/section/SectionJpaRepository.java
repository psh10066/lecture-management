package com.psh10066.lecturemanagement.lecture.adapter.out.persistence.section;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SectionJpaRepository extends JpaRepository<SectionJpaEntity, Long> {
    List<SectionJpaEntity> findAllByCurriculumIdIn(List<Long> curriculumIds);
}
