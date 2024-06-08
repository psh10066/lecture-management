package com.psh10066.lecturemanagement.domain.lecture;

import com.psh10066.lecturemanagement.domain.lecture.type.LecturePlatform;
import com.psh10066.lecturemanagement.presentation.dto.LectureListDTO;
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
        return lectureJpaRepository.findAllByUser(user);
    }

    @Override
    public Lecture save(Lecture lecture) {
        return lectureJpaRepository.save(lecture);
    }

    @Override
    public List<LectureListDTO> findAllLecture(User user, String lectureName, LecturePlatform lecturePlatform) {
        return lectureCustomRepository.findAllLecture(user, lectureName, lecturePlatform);
    }

    @Override
    public Lecture findFetchByLectureInfo(Long lectureId) {
        return lectureCustomRepository.findFetchByLectureInfo(lectureId);
    }
}
