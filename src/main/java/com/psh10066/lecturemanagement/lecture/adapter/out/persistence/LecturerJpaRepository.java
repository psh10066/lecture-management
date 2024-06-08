package com.psh10066.lecturemanagement.lecture.adapter.out.persistence;

import com.psh10066.lecturemanagement.lecture.domain.Lecturer;
import com.psh10066.lecturemanagement.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LecturerJpaRepository extends JpaRepository<Lecturer, Long> {

    Optional<Lecturer> findByLecturerNameAndUser(String lecturerName, User user);
}
