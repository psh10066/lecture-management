package com.psh10066.lecturemanagement.lecture.application.port.out;

import com.psh10066.lecturemanagement.lecture.domain.Lecturer;
import com.psh10066.lecturemanagement.user.domain.User;

public interface LecturerRepository {

    Lecturer findOrRegister(String lecturerName, User user);
}
