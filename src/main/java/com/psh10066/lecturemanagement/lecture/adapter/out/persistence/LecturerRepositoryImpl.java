package com.psh10066.lecturemanagement.lecture.adapter.out.persistence;

import com.psh10066.lecturemanagement.lecture.adapter.out.persistence.lecturer.LecturerJpaEntity;
import com.psh10066.lecturemanagement.lecture.adapter.out.persistence.lecturer.LecturerJpaRepository;
import com.psh10066.lecturemanagement.lecture.application.port.out.LecturerRepository;
import com.psh10066.lecturemanagement.lecture.domain.Lecturer;
import com.psh10066.lecturemanagement.user.domain.User;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class LecturerRepositoryImpl implements LecturerRepository {

    private final LecturerJpaRepository lecturerJpaRepository;

    @Override
    public Optional<Lecturer> findByLecturerNameAndUser(String lecturerName, User user) {
        return lecturerJpaRepository.findByLecturerNameAndUserId(lecturerName, user.getUserId()).map(LecturerJpaEntity::toModel);
    }

    @Override
    public Lecturer getById(Long id) {
        return lecturerJpaRepository.findById(id)
            .orElseThrow(EntityNotFoundException::new)
            .toModel();
    }

    @Override
    public Lecturer save(Lecturer lecturer) {
        return lecturerJpaRepository.save(LecturerJpaEntity.from(lecturer)).toModel();
    }
}
