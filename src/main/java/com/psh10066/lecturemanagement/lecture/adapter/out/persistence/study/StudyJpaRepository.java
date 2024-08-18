package com.psh10066.lecturemanagement.lecture.adapter.out.persistence.study;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudyJpaRepository extends JpaRepository<StudyJpaEntity, Long>, StudyCustomRepository {

    List<StudyJpaEntity> findAllBySectionIdIn(List<Long> sectionIds);
}
