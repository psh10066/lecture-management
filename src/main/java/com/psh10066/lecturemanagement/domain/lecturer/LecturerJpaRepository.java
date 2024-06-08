package com.psh10066.lecturemanagement.domain.lecturer;

import com.psh10066.lecturemanagement.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LecturerJpaRepository extends JpaRepository<Lecturer, Long> {

    Optional<Lecturer> findByLecturerNameAndUser(String lecturerName, User user);
}
