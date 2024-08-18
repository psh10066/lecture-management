package com.psh10066.lecturemanagement.lecture.application.service;

import com.psh10066.lecturemanagement.core.util.DateTimeUtil;
import com.psh10066.lecturemanagement.lecture.adapter.in.web.request.RegisterInflearnLectureRequest;
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
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
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
        return lectureRepository.findFetchByLectureModifyInfo(lectureId);
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

    @Transactional
    public Lecture registerInflearnLecture(User user, RegisterInflearnLectureRequest request) {
        String prefix = "https://www.inflearn.com/course/";
        if (!request.lecturePath().startsWith(prefix)) {
            throw new IllegalArgumentException("잘못된 강의 경로입니다.");
        }

        String lecturePath = URLDecoder.decode(prefix + request.lecturePath().substring(prefix.length()).split("/")[0].split("\\?")[0].split("#")[0], StandardCharsets.UTF_8);
        Document baseDoc;
        try {
            baseDoc = Jsoup.connect(lecturePath).get();
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }

        Elements sections = baseDoc.getElementsByClass("cd-accordion__section-cover");
        if (sections.isEmpty()) {
            throw new IllegalArgumentException("잘못된 강의 경로입니다.");
        }

        String lectureName = baseDoc.getElementsByClass("cd-header__title").getFirst().ownText();
        String lecturerName = baseDoc.getElementsByClass("cd-header__instructors--main").getFirst().text();

        Lecture lecture = lectureRepository.save(Lecture.createLecture(lectureName, LecturePlatform.INFLEARN, lecturePath + "/dashboard", user.getUserId()));
        Lecturer lecturer = lecturerRepository.findByLecturerNameAndUser(lecturerName, user)
            .orElseGet(() -> lecturerRepository.save(Lecturer.createLecturer(lecturerName, user.getUserId())));
        Curriculum curriculum = curriculumRepository.save(Curriculum.createCurriculum(lectureName, lecturer.lecturerId()));
        lectureToCurriculumRepository.save(LectureToCurriculum.createLectureToCurriculum(lecture.lectureId(), curriculum.curriculumId()));

        for (Element sectionElement : sections) {
            String sectionName = sectionElement.getElementsByClass("cd-accordion__section-title").getFirst().text();
            Section section = sectionRepository.save(Section.createSection(sectionName, curriculum.curriculumId()));

            Elements studies = sectionElement.getElementsByClass("cd-accordion__unit");
            for (Element studyElement : studies) {
                String studyName = studyElement.getElementsByClass("ac-accordion__unit-title").getFirst().text();
                Element studyTimeElement = studyElement.getElementsByClass("ac-accordion__unit-info--time").first();
                String studyTime = studyTimeElement != null ? studyTimeElement.text() : null;
                studyRepository.save(Study.createStudy(studyName, DateTimeUtil.parseTime(studyTime), section.sectionId()));
            }
        }
        return lecture;
    }
}
