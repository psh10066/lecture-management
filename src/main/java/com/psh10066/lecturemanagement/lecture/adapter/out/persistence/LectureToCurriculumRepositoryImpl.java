package com.psh10066.lecturemanagement.lecture.adapter.out.persistence;

import com.psh10066.lecturemanagement.lecture.application.port.out.LectureToCurriculumRepository;
import com.psh10066.lecturemanagement.lecture.domain.LectureToCurriculum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class LectureToCurriculumRepositoryImpl implements LectureToCurriculumRepository {

    private final LectureToCurriculumJpaRepository lectureToCurriculumJpaRepository;

    @Override
    public LectureToCurriculum save(LectureToCurriculum lectureToCurriculum) {
        return lectureToCurriculumJpaRepository.save(LectureToCurriculumJpaEntity.from(lectureToCurriculum)).toModel();
    }
}
