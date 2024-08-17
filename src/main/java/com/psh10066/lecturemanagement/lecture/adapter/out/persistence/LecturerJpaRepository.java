package com.psh10066.lecturemanagement.lecture.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LecturerJpaRepository extends JpaRepository<LecturerJpaEntity, Long> {

    Optional<LecturerJpaEntity> findByLecturerNameAndUserId(String lecturerName, Long userId);

    List<LecturerJpaEntity> findAllByLecturerIdIn(List<Long> lecturerIds);
}
