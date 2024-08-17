package com.psh10066.lecturemanagement.lecture.adapter.out.persistence;

import com.psh10066.lecturemanagement.lecture.application.port.in.dto.LectureModifyInfoDTO;
import com.psh10066.lecturemanagement.lecture.application.port.in.dto.LectureInfoDTO;
import com.psh10066.lecturemanagement.lecture.application.port.out.LectureRepository;
import com.psh10066.lecturemanagement.lecture.domain.Lecture;
import com.psh10066.lecturemanagement.lecture.domain.LecturePlatform;
import com.psh10066.lecturemanagement.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class LectureRepositoryImpl implements LectureRepository {

    private final LectureJpaRepository lectureJpaRepository;
    private final LectureCustomRepository lectureCustomRepository;

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
    public LectureInfoDTO findFetchByLectureInfo(Long lectureId) {
        return lectureCustomRepository.findFetchByLectureInfo(lectureId);
    }

    @Override
    public LectureModifyInfoDTO findFetchByLectureModifyInfo(Long lectureId) {
        LectureInfoDTO lectureInfo = lectureCustomRepository.findFetchByLectureInfo(lectureId);
        return LectureMapper.INSTANCE.toLectureModifyInfo(lectureInfo);
    }
}
