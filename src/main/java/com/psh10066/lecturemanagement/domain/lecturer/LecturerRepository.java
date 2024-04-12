package com.psh10066.lecturemanagement.domain.lecturer;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LecturerRepository extends JpaRepository<Lecturer, Long> {
    List<Lecturer> findAllByOrderByLecturerNameAsc();
}
