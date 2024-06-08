package com.psh10066.lecturemanagement.domain.lecture;

import com.psh10066.lecturemanagement.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LectureJpaRepository extends JpaRepository<Lecture, Long> {

    List<Lecture> findAllByUser(User user);
}
