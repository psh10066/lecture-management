package com.psh10066.lecturemanagement.lecture.application.service;

import com.psh10066.lecturemanagement.lecture.application.port.in.LectureService;
import com.psh10066.lecturemanagement.lecture.application.port.in.command.LecturesCommand;
import com.psh10066.lecturemanagement.lecture.application.port.in.command.ModifyLectureCommand;
import com.psh10066.lecturemanagement.lecture.application.port.in.command.RegisterLectureCommand;
import com.psh10066.lecturemanagement.lecture.application.port.in.dto.LectureInfoDTO;
import com.psh10066.lecturemanagement.lecture.application.port.in.dto.LectureModifyInfoDTO;
import com.psh10066.lecturemanagement.lecture.application.port.out.CurriculumRepository;
import com.psh10066.lecturemanagement.lecture.application.port.out.LectureRepository;
import com.psh10066.lecturemanagement.lecture.domain.Lecture;
import com.psh10066.lecturemanagement.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LectureServiceImpl implements LectureService {

    private final LectureRepository lectureRepository;
    private final CurriculumRepository curriculumRepository;

    public List<Lecture> lectureList(User user) {
        return lectureRepository.findAllByUser(user);
    }

    public List<Lecture> lectureList(User user, LecturesCommand command) {
        return lectureRepository.findAllLecture(user, command.lectureName(), command.lecturePlatform());
    }

    @Transactional(readOnly = true)
    public LectureInfoDTO lectureInfo(Long lectureId) {
        return lectureRepository.findByLectureInfo(lectureId);
    }

    @Transactional(readOnly = true)
    public LectureModifyInfoDTO lectureModifyInfo(Long lectureId) {
        return lectureRepository.findByLectureModifyInfo(lectureId);
    }

    @Transactional
    public void modifyLecture(User user, ModifyLectureCommand command) {
        curriculumRepository.updateLecturer(command, user);
    }

    @Transactional
    public Lecture registerLecture(User user, RegisterLectureCommand command) {
        Lecture lecture = lectureRepository.save(Lecture.createLecture(command.lectureName(), command.lecturePlatform(), command.lecturePath(), user.getUserId()));

        command.curriculumList().forEach(curriculumDTO ->
            curriculumRepository.register(user, lecture.lectureId(), curriculumDTO)
        );

        return lecture;
    }
}
