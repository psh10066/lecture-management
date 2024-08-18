package com.psh10066.lecturemanagement.lecture.adapter.out.persistence;

import com.psh10066.lecturemanagement.lecture.adapter.out.persistence.study.StudyJpaEntity;
import com.psh10066.lecturemanagement.lecture.adapter.out.persistence.study.StudyJpaRepository;
import com.psh10066.lecturemanagement.lecture.application.port.in.command.RegisterLectureCommand;
import com.psh10066.lecturemanagement.lecture.application.port.in.dto.LectureInfoDTO;
import com.psh10066.lecturemanagement.lecture.application.port.in.dto.StudyListDTO;
import com.psh10066.lecturemanagement.lecture.application.port.out.StudyRepository;
import com.psh10066.lecturemanagement.lecture.domain.LecturePlatform;
import com.psh10066.lecturemanagement.lecture.domain.Study;
import com.psh10066.lecturemanagement.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
@RequiredArgsConstructor
public class StudyRepositoryImpl implements StudyRepository {

    private final StudyJpaRepository studyJpaRepository;

    @Override
    public Page<StudyListDTO> findAllStudy(User user, Pageable pageable, LecturePlatform lecturePlatform, Long lectureId, String lecturerName, String studyName) {
        return studyJpaRepository.findAllStudy(user, pageable, lecturePlatform, lectureId, lecturerName, studyName);
    }

    @Override
    public void register(Long sectionId, RegisterLectureCommand.StudyDTO studyDTO) {
        Study study = Study.createStudy(studyDTO.studyName(), studyDTO.studyTime(), sectionId);
        studyJpaRepository.save(StudyJpaEntity.from(study));
    }

    @Override
    public List<LectureInfoDTO.StudyDTO> findAllBySectionIdIn(List<Long> sectionIds) {
        return studyJpaRepository.findAllBySectionIdIn(sectionIds).stream()
            .map(study -> new LectureInfoDTO.StudyDTO(
                study.getStudyId(),
                study.getSectionId(),
                study.getStudyName(),
                study.getStudyTime()
            ))
            .toList();
    }
}
