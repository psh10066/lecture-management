package com.psh10066.lecturemanagement.domain.lecturer;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LecturerRepository extends JpaRepository<Lecturer, Long> {
    List<Lecturer> findAllByOrderByLecturerNameAsc();

    Optional<Lecturer> findByLecturerName(String lecturerName);
}
