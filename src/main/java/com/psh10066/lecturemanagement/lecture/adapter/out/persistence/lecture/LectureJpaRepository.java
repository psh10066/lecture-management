package com.psh10066.lecturemanagement.lecture.adapter.out.persistence.lecture;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LectureJpaRepository extends JpaRepository<LectureJpaEntity, Long>, LectureCustomRepository {

    List<LectureJpaEntity> findAllByUserId(Long userId);
}
