package com.psh10066.lecturemanagement.lecture.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LectureJpaRepository extends JpaRepository<LectureJpaEntity, Long> {

    List<LectureJpaEntity> findAllByUserId(Long userId);
}
