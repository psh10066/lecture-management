package com.psh10066.lecturemanagement.domain.study;

import com.psh10066.lecturemanagement.domain.lecture.type.LecturePlatform;
import com.psh10066.lecturemanagement.presentation.dto.StudyListDTO;
import com.psh10066.lecturemanagement.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class StudyRepositoryImpl implements StudyRepository {

    private final StudyJpaRepository studyJpaRepository;
    private final StudyCustomRepository studyCustomRepository;

    @Override
    public Study save(Study study) {
        return studyJpaRepository.save(study);
    }

    @Override
    public Page<StudyListDTO> findAllStudy(User user, Pageable pageable, LecturePlatform lecturePlatform, Long lectureId, String lecturerName, String studyName) {
        return studyCustomRepository.findAllStudy(user, pageable, lecturePlatform, lectureId, lecturerName, studyName);
    }
}
