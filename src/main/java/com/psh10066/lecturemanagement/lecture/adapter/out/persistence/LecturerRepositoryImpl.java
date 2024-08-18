package com.psh10066.lecturemanagement.lecture.adapter.out.persistence;

import com.psh10066.lecturemanagement.lecture.adapter.out.persistence.lecturer.LecturerJpaEntity;
import com.psh10066.lecturemanagement.lecture.adapter.out.persistence.lecturer.LecturerJpaRepository;
import com.psh10066.lecturemanagement.lecture.application.port.out.LecturerRepository;
import com.psh10066.lecturemanagement.lecture.domain.Lecturer;
import com.psh10066.lecturemanagement.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@RequiredArgsConstructor
public class LecturerRepositoryImpl implements LecturerRepository {

    private final LecturerJpaRepository lecturerJpaRepository;

    @Override
    public Lecturer findOrRegister(String lecturerName, User user) {
        return lecturerJpaRepository.findByLecturerNameAndUserId(lecturerName, user.getUserId())
            .orElseGet(() ->
                lecturerJpaRepository.save(LecturerJpaEntity.from(
                    Lecturer.createLecturer(lecturerName, user.getUserId())
                ))
            )
            .toModel();
    }
}
