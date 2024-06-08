package com.psh10066.lecturemanagement.lecture.application.port.out;

import com.psh10066.lecturemanagement.lecture.domain.Lecturer;
import com.psh10066.lecturemanagement.user.domain.User;

import java.util.Optional;

public interface LecturerRepository {

    Optional<Lecturer> findByLecturerNameAndUser(String lecturerName, User user);

    Lecturer save(Lecturer lecturer);
}
