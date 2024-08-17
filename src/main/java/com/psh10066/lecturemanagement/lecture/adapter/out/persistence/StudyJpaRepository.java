package com.psh10066.lecturemanagement.lecture.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudyJpaRepository extends JpaRepository<StudyJpaEntity, Long> {

    List<StudyJpaEntity> findAllBySectionIdIn(List<Long> sectionIds);
}
