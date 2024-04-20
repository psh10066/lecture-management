package com.psh10066.lecturemanagement.domain.lecturer;

import com.psh10066.lecturemanagement.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LecturerRepository extends JpaRepository<Lecturer, Long> {
    List<Lecturer> findAllByUserOrderByLecturerNameAsc(User user);

    Optional<Lecturer> findByLecturerNameAndUser(String lecturerName, User user);
}
