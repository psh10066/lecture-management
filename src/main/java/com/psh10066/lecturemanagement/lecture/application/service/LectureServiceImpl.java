package com.psh10066.lecturemanagement.lecture.application.service;

import com.psh10066.lecturemanagement.lecture.application.port.in.LectureService;
import com.psh10066.lecturemanagement.lecture.application.port.in.command.LecturesCommand;
import com.psh10066.lecturemanagement.lecture.application.port.in.command.ModifyLectureCommand;
import com.psh10066.lecturemanagement.lecture.application.port.in.command.RegisterLectureCommand;
import com.psh10066.lecturemanagement.lecture.application.port.in.dto.LectureInfoDTO;
import com.psh10066.lecturemanagement.lecture.application.port.in.dto.LectureModifyInfoDTO;
import com.psh10066.lecturemanagement.lecture.application.port.out.*;
import com.psh10066.lecturemanagement.lecture.domain.*;
import com.psh10066.lecturemanagement.user.domain.User;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LectureServiceImpl implements LectureService {

    private final LectureRepository lectureRepository;
    private final CurriculumRepository curriculumRepository;
    private final LectureToCurriculumRepository lectureToCurriculumRepository;
    private final SectionRepository sectionRepository;
    private final StudyRepository studyRepository;
    private final LecturerRepository lecturerRepository;

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

        command.curriculumList().forEach(curriculumDTO -> {
            Long lecturerId = null;
            if (StringUtils.isNotBlank(curriculumDTO.lecturerName())) {
                lecturerId = lecturerRepository.findByLecturerNameAndUser(curriculumDTO.lecturerName(), user)
                    .orElseGet(() -> lecturerRepository.save(Lecturer.createLecturer(curriculumDTO.lecturerName(), user.getUserId())))
                    .lecturerId();
            }

            Curriculum curriculum = curriculumRepository.save(Curriculum.createCurriculum(curriculumDTO.curriculumName(), lecturerId));
            lectureToCurriculumRepository.save(LectureToCurriculum.createLectureToCurriculum(lecture.lectureId(), curriculum.curriculumId()));

            curriculumDTO.sectionList().forEach(sectionDTO -> {
                Section section = sectionRepository.save(Section.createSection(sectionDTO.sectionName(), curriculum.curriculumId()));

                sectionDTO.studyList().forEach(studyDTO ->
                    studyRepository.save(Study.createStudy(studyDTO.studyName(), studyDTO.studyTime(), section.sectionId()))
                );
            });
        });

        return lecture;
    }
}
