package com.psh10066.lecturemanagement.domain.lecturetocurriculum;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class LectureToCurriculumRepositoryImpl implements LectureToCurriculumRepository {

    private final LectureToCurriculumJpaRepository lectureToCurriculumJpaRepository;

    @Override
    public LectureToCurriculum save(LectureToCurriculum lectureToCurriculum) {
        return lectureToCurriculumJpaRepository.save(lectureToCurriculum);
    }
}
