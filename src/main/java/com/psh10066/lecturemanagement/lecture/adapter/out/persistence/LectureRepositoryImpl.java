package com.psh10066.lecturemanagement.lecture.adapter.out.persistence;

import com.psh10066.lecturemanagement.lecture.adapter.out.persistence.lecture.LectureJpaEntity;
import com.psh10066.lecturemanagement.lecture.adapter.out.persistence.lecture.LectureJpaRepository;
import com.psh10066.lecturemanagement.lecture.application.port.in.dto.LectureInfoDTO;
import com.psh10066.lecturemanagement.lecture.application.port.in.dto.LectureModifyInfoDTO;
import com.psh10066.lecturemanagement.lecture.application.port.out.CurriculumRepository;
import com.psh10066.lecturemanagement.lecture.application.port.out.LectureRepository;
import com.psh10066.lecturemanagement.lecture.domain.Lecture;
import com.psh10066.lecturemanagement.lecture.domain.LecturePlatform;
import com.psh10066.lecturemanagement.user.domain.User;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
@RequiredArgsConstructor
public class LectureRepositoryImpl implements LectureRepository {

    private final LectureJpaRepository lectureJpaRepository;
    private final CurriculumRepository curriculumRepository;

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
        return lectureJpaRepository.findAllLecture(user, lectureName, lecturePlatform);
    }

    @Override
    public LectureInfoDTO findByLectureInfo(Long lectureId) {
        LectureJpaEntity lectureJpaEntity = lectureJpaRepository.findById(lectureId)
            .orElseThrow(EntityNotFoundException::new);

        List<LectureInfoDTO.CurriculumDTO> curriculumList = curriculumRepository.findInfoByLectureId(lectureId);

        return new LectureInfoDTO(
            lectureId,
            lectureJpaEntity.getLectureName(),
            lectureJpaEntity.getLecturePlatform(),
            lectureJpaEntity.getLecturePath(),
            curriculumList
        );
    }

    @Override
    public LectureModifyInfoDTO findByLectureModifyInfo(Long lectureId) {
        LectureJpaEntity lectureJpaEntity = lectureJpaRepository.findById(lectureId)
            .orElseThrow(EntityNotFoundException::new);

        List<LectureModifyInfoDTO.CurriculumDTO> curriculumList = curriculumRepository.findModifyInfoByLectureId(lectureId);

        return new LectureModifyInfoDTO(
            lectureId,
            lectureJpaEntity.getLectureName(),
            lectureJpaEntity.getLecturePlatform(),
            lectureJpaEntity.getLecturePath(),
            curriculumList
        );
    }
}
