package com.psh10066.lecturemanagement.domain.lecture;

import com.psh10066.lecturemanagement.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LectureRepository extends JpaRepository<Lecture, Long>, LectureCustomRepository {

    List<Lecture> findAllByUser(User user);
}
