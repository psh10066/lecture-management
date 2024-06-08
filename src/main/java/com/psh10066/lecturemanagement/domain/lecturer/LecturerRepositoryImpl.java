package com.psh10066.lecturemanagement.domain.lecturer;

import com.psh10066.lecturemanagement.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class LecturerRepositoryImpl implements LecturerRepository {

    private final LecturerJpaRepository lecturerJpaRepository;

    @Override
    public Optional<Lecturer> findByLecturerNameAndUser(String lecturerName, User user) {
        return lecturerJpaRepository.findByLecturerNameAndUser(lecturerName, user);
    }

    @Override
    public Lecturer save(Lecturer lecturer) {
        return lecturerJpaRepository.save(lecturer);
    }
}
