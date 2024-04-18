package com.psh10066.lecturemanagement.application;

import com.psh10066.lecturemanagement.domain.curriculum.Curriculum;
import com.psh10066.lecturemanagement.domain.curriculum.CurriculumRepository;
import com.psh10066.lecturemanagement.domain.lecture.Lecture;
import com.psh10066.lecturemanagement.domain.lecture.LectureRepository;
import com.psh10066.lecturemanagement.domain.lecture.type.LecturePlatform;
import com.psh10066.lecturemanagement.domain.lecturetocurriculum.LectureToCurriculum;
import com.psh10066.lecturemanagement.domain.lecturetocurriculum.LectureToCurriculumRepository;
import com.psh10066.lecturemanagement.domain.section.Section;
import com.psh10066.lecturemanagement.domain.section.SectionRepository;
import com.psh10066.lecturemanagement.domain.study.Study;
import com.psh10066.lecturemanagement.domain.study.StudyRepository;
import com.psh10066.lecturemanagement.infrastructure.util.DateTimeUtil;
import com.psh10066.lecturemanagement.presentation.dto.LectureSelectDTO;
import com.psh10066.lecturemanagement.presentation.dto.RegisterFastcampusLectureRequest;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LectureService {

    private final LectureRepository lectureRepository;
    private final CurriculumRepository curriculumRepository;
    private final LectureToCurriculumRepository lectureToCurriculumRepository;
    private final SectionRepository sectionRepository;
    private final StudyRepository studyRepository;

    public List<LectureSelectDTO> lectureList() {
        return lectureRepository.findAll().stream()
            .map(LectureSelectDTO::from)
            .toList();
    }

    @Transactional
    public void registerFastcampusLecture(RegisterFastcampusLectureRequest request) {
        Lecture lecture = lectureRepository.save(Lecture.createLecture(request.lectureName(), LecturePlatform.FASTCAMPUS, request.lecturePath()));
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
}
