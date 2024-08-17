package com.psh10066.lecturemanagement.lecture.adapter.out.persistence;

import com.psh10066.lecturemanagement.lecture.application.port.in.dto.LectureInfoDTO;
import com.psh10066.lecturemanagement.lecture.application.port.in.dto.LectureModifyInfoDTO;
import com.psh10066.lecturemanagement.lecture.application.port.out.LectureRepository;
import com.psh10066.lecturemanagement.lecture.domain.Curriculum;
import com.psh10066.lecturemanagement.lecture.domain.Lecture;
import com.psh10066.lecturemanagement.lecture.domain.LecturePlatform;
import com.psh10066.lecturemanagement.user.domain.User;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class LectureRepositoryImpl implements LectureRepository {

    private final LectureJpaRepository lectureJpaRepository;
    private final LectureCustomRepository lectureCustomRepository;
    private final CurriculumCustomRepository curriculumCustomRepository;
    private final SectionJpaRepository sectionJpaRepository;
    private final StudyJpaRepository studyJpaRepository;
    private final LecturerJpaRepository lecturerJpaRepository;

    @Override
    public List<Lecture> findAllByUser(User user) {
        return lectureJpaRepository.findAllByUserId(user.getUserId()).stream()
            .map(LectureJpaEntity::toModel)
            .toList();
    }

    @Override
    public Lecture save(Lecture lecture) {
        return lectureJpaRepository.save(LectureJpaEntity.from(lecture)).toModel();
    }

    @Override
    public List<Lecture> findAllLecture(User user, String lectureName, LecturePlatform lecturePlatform) {
        return lectureCustomRepository.findAllLecture(user, lectureName, lecturePlatform);
    }

    @Override
    public LectureInfoDTO findByLectureInfo(Long lectureId) {
        Lecture lecture = lectureJpaRepository.findById(lectureId)
            .orElseThrow(EntityNotFoundException::new)
            .toModel();

        List<Curriculum> curriculumList = curriculumCustomRepository.findAllByLectureId(lectureId);
        List<Long> curriculumIds = curriculumList.stream()
            .map(Curriculum::curriculumId)
            .toList();
        List<Long> lecturerIds = curriculumList.stream()
            .map(Curriculum::lecturerId)
            .toList();

        Map<Long, String> lecturerNameMap = lecturerJpaRepository.findAllByLecturerIdIn(lecturerIds).stream()
            .collect(Collectors.toMap(
                LecturerJpaEntity::getLecturerId,
                LecturerJpaEntity::getLecturerName
            ));

        List<SectionJpaEntity> sectionList = sectionJpaRepository.findAllByCurriculumIdIn(curriculumIds);
        List<Long> sectionIds = sectionList.stream()
            .map(SectionJpaEntity::getSectionId)
            .toList();

        List<StudyJpaEntity> studyList = studyJpaRepository.findAllBySectionIdIn(sectionIds);

        return new LectureInfoDTO(
            lectureId,
            lecture.lectureName(),
            lecture.lecturePlatform(),
            lecture.lecturePath(),
            curriculumList.stream()
                .map(curriculum -> new LectureInfoDTO.CurriculumDTO(
                    curriculum.curriculumId(),
                    curriculum.curriculumName(),
                    lecturerNameMap.get(curriculum.lecturerId()),
                    sectionList.stream()
                        .filter(sectionJpaEntity -> sectionJpaEntity.getCurriculumId().equals(curriculum.curriculumId()))
                        .map(sectionJpaEntity -> new LectureInfoDTO.SectionDTO(
                            sectionJpaEntity.getSectionId(),
                            sectionJpaEntity.getSectionName(),
                            studyList.stream()
                                .filter(studyJpaEntity -> studyJpaEntity.getSectionId().equals(sectionJpaEntity.getSectionId()))
                                .map(studyJpaEntity -> new LectureInfoDTO.StudyDTO(
                                    studyJpaEntity.getStudyId(),
                                    studyJpaEntity.getStudyName(),
                                    studyJpaEntity.getStudyTime()
                                ))
                                .toList()
                        ))
                        .toList()
                ))
                .toList()
        );
    }

    @Override
    public LectureModifyInfoDTO findFetchByLectureModifyInfo(Long lectureId) {
        Lecture lecture = lectureJpaRepository.findById(lectureId)
            .orElseThrow(EntityNotFoundException::new)
            .toModel();

        List<Curriculum> curriculumList = curriculumCustomRepository.findAllByLectureId(lectureId);
        List<Long> lecturerIds = curriculumList.stream()
            .map(Curriculum::lecturerId)
            .toList();

        Map<Long, String> lecturerNameMap = lecturerJpaRepository.findAllByLecturerIdIn(lecturerIds).stream()
            .collect(Collectors.toMap(
                LecturerJpaEntity::getLecturerId,
                LecturerJpaEntity::getLecturerName
            ));

        return new LectureModifyInfoDTO(
            lectureId,
            lecture.lectureName(),
            lecture.lecturePlatform(),
            lecture.lecturePath(),
            curriculumList.stream()
                .map(curriculum -> new LectureModifyInfoDTO.CurriculumDTO(
                    curriculum.curriculumId(),
                    curriculum.curriculumName(),
                    lecturerNameMap.get(curriculum.lecturerId())
                ))
                .toList()
        );
    }
}
