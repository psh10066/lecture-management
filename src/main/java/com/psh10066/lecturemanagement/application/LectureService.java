package com.psh10066.lecturemanagement.application;

import com.psh10066.lecturemanagement.domain.curriculum.Curriculum;
import com.psh10066.lecturemanagement.domain.curriculum.CurriculumRepository;
import com.psh10066.lecturemanagement.domain.lecture.Lecture;
import com.psh10066.lecturemanagement.domain.lecture.LectureMapper;
import com.psh10066.lecturemanagement.domain.lecture.LectureRepository;
import com.psh10066.lecturemanagement.domain.lecture.type.LecturePlatform;
import com.psh10066.lecturemanagement.domain.lecturer.Lecturer;
import com.psh10066.lecturemanagement.domain.lecturer.LecturerRepository;
import com.psh10066.lecturemanagement.domain.lecturetocurriculum.LectureToCurriculum;
import com.psh10066.lecturemanagement.domain.lecturetocurriculum.LectureToCurriculumRepository;
import com.psh10066.lecturemanagement.domain.section.Section;
import com.psh10066.lecturemanagement.domain.section.SectionRepository;
import com.psh10066.lecturemanagement.domain.study.Study;
import com.psh10066.lecturemanagement.domain.study.StudyRepository;
import com.psh10066.lecturemanagement.domain.user.User;
import com.psh10066.lecturemanagement.infrastructure.util.DateTimeUtil;
import com.psh10066.lecturemanagement.presentation.dto.*;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LectureService {

    private final LectureRepository lectureRepository;
    private final CurriculumRepository curriculumRepository;
    private final LectureToCurriculumRepository lectureToCurriculumRepository;
    private final SectionRepository sectionRepository;
    private final StudyRepository studyRepository;
    private final LecturerRepository lecturerRepository;

    public List<LectureListDTO> lectureList(User user) {
        return lectureRepository.findAllByUser(user).stream()
            .map(LectureListDTO::from)
            .toList();
    }

    public Page<LectureListDTO> lectureList(User user, Pageable pageable, LecturesRequest request) {
        return lectureRepository.findAllLecture(user, pageable, request.lectureName(), request.lecturePlatform());
    }

    @Transactional(readOnly = true)
    public LectureInfoDTO lectureInfo(Long lectureId) {
        Lecture lecture = lectureRepository.findFetchByLectureInfo(lectureId);
        return LectureMapper.INSTANCE.toLectureInfo(lecture);
    }

    @Transactional(readOnly = true)
    public LectureModifyInfoDTO lectureModifyInfo(Long lectureId) {
        Lecture lecture = lectureRepository.findFetchByLectureInfo(lectureId);
        return LectureMapper.INSTANCE.toLectureModifyInfo(lecture);
    }

    @Transactional
    public void modifyLecture(User user, Long lectureId, ModifyLectureRequest request) {
        Lecture lecture = lectureRepository.findFetchByLectureInfo(lectureId);
        Map<Long, Curriculum> curriculumMap = lecture.getLectureToCurriculumList().stream()
            .collect(Collectors.toMap(
                o -> o.getCurriculum().getCurriculumId(),
                LectureToCurriculum::getCurriculum
            ));

        request.getCurriculumList().forEach(input -> {
            if (curriculumMap.containsKey(input.getCurriculumId())) {
                Curriculum curriculum = curriculumMap.get(input.getCurriculumId());
                Lecturer lecturer = StringUtils.isNotBlank(input.getLecturerName())
                    ? lecturerRepository.findByLecturerNameAndUser(input.getLecturerName(), user)
                    .orElseGet(() -> lecturerRepository.save(Lecturer.createLecturer(input.getLecturerName(), user)))
                    : null;
                curriculum.updateCurriculum(lecturer);
            }
        });
    }

    @Transactional
    public void registerFastcampusLecture(User user, RegisterFastcampusLectureRequest request) {
        Lecture lecture = lectureRepository.save(Lecture.createLecture(request.lectureName(), LecturePlatform.FASTCAMPUS, request.lecturePath(), user));
        String[] split = request.lectureInfo().split("\r\n");
        Curriculum curriculum = null;
        Section section = null;

        for (int i = 0; i < split.length; i++) {
            String s = split[i];
            if (StringUtils.isBlank(s) || s.startsWith("미제출")) {
                continue;
            }

            // curriculum
            try {
                Long.parseLong(s); // curriculum 체크용
                curriculum = curriculumRepository.save(Curriculum.createCurriculum(split[i + 1], null));
                lectureToCurriculumRepository.save(LectureToCurriculum.createLectureToCurriculum(lecture, curriculum));
                i += 5;
                section = null;
                continue;
            } catch (Exception ignored) {
            }

            // section
            if (StringUtils.isBlank(split[i + 1])) {
                section = sectionRepository.save(Section.createSection(s, curriculum));
                i += 2;
                continue;
            } else if (section == null) {
                section = sectionRepository.save(Section.createSection(curriculum.getCurriculumName(), curriculum));
            }

            // study
            studyRepository.save(Study.createStudy(s, DateTimeUtil.parseTime(split[i + 1]), section));
            i++;
        }
    }

    @Transactional
    public void registerInflearnLecture(User user, RegisterInflearnLectureRequest request) {
        String prefix = "https://www.inflearn.com/course/";
        if (!request.lecturePath().startsWith(prefix)) {
            throw new RuntimeException("잘못된 강의 경로입니다.");
        }

        String lecturePath = URLDecoder.decode(prefix + request.lecturePath().substring(prefix.length()).split("/")[0].split("\\?")[0].split("#")[0], StandardCharsets.UTF_8);
        Document baseDoc;
        try {
            baseDoc = Jsoup.connect(lecturePath).get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Elements sections = baseDoc.getElementsByClass("cd-accordion__section-cover");
        if (sections.isEmpty()) {
            throw new RuntimeException("잘못된 강의 경로입니다.");
        }

        String lectureName = baseDoc.getElementsByClass("cd-header__title").getFirst().ownText();
        String lecturerName = baseDoc.getElementsByClass("cd-header__instructors--main").getFirst().text();

        Lecture lecture = lectureRepository.save(Lecture.createLecture(lectureName, LecturePlatform.INFLEARN, lecturePath + "/dashboard", user));
        Lecturer lecturer = lecturerRepository.findByLecturerNameAndUser(lecturerName, user)
            .orElseGet(() -> lecturerRepository.save(Lecturer.createLecturer(lecturerName, user)));
        Curriculum curriculum = curriculumRepository.save(Curriculum.createCurriculum(lectureName, lecturer));
        lectureToCurriculumRepository.save(LectureToCurriculum.createLectureToCurriculum(lecture, curriculum));

        for (Element sectionElement : sections) {
            String sectionName = sectionElement.getElementsByClass("cd-accordion__section-title").getFirst().text();
            Section section = sectionRepository.save(Section.createSection(sectionName, curriculum));

            Elements studies = sectionElement.getElementsByClass("cd-accordion__unit");
            for (Element studyElement : studies) {
                String studyName = studyElement.getElementsByClass("ac-accordion__unit-title").getFirst().text();
                Element studyTimeElement = studyElement.getElementsByClass("ac-accordion__unit-info--time").first();
                String studyTime = studyTimeElement != null ? studyTimeElement.text() : null;
                studyRepository.save(Study.createStudy(studyName, DateTimeUtil.parseTime(studyTime), section));
            }
        }
    }
}
