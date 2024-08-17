package com.psh10066.lecturemanagement.lecture.adapter.out.persistence;

import com.psh10066.lecturemanagement.lecture.application.port.in.dto.StudyListDTO;
import com.psh10066.lecturemanagement.lecture.application.port.out.StudyRepository;
import com.psh10066.lecturemanagement.lecture.domain.LecturePlatform;
import com.psh10066.lecturemanagement.lecture.domain.Study;
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
        return studyJpaRepository.save(StudyJpaEntity.from(study)).toModel();
    }

    @Override
    public Page<StudyListDTO> findAllStudy(User user, Pageable pageable, LecturePlatform lecturePlatform, Long lectureId, String lecturerName, String studyName) {
        return studyCustomRepository.findAllStudy(user, pageable, lecturePlatform, lectureId, lecturerName, studyName);
    }
}
