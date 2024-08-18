package com.psh10066.lecturemanagement.lecture.adapter.in.web.factory;

import com.psh10066.lecturemanagement.core.util.DateTimeUtil;
import com.psh10066.lecturemanagement.lecture.adapter.in.web.request.RegisterFastcampusLectureRequest;
import com.psh10066.lecturemanagement.lecture.application.port.in.command.RegisterLectureCommand;
import com.psh10066.lecturemanagement.lecture.domain.LecturePlatform;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class RegisterLectureCommandFactory {

    public RegisterLectureCommand fromFastcampusRequest(RegisterFastcampusLectureRequest request) {
        List<RegisterLectureCommand.CurriculumDTO> curriculumList = new ArrayList<>();

        String[] split = request.lectureInfo().split("\r\n");

        RegisterLectureCommand.CurriculumDTO curriculumDTO = null;
        RegisterLectureCommand.SectionDTO sectionDTO = null;

        for (int i = 0; i < split.length; i++) {
            String s = split[i];
            if (StringUtils.isBlank(s) || s.startsWith("미제출")) {
                continue;
            }

            // curriculum
            try {
                Long.parseLong(s); // curriculum 체크용

                curriculumDTO = new RegisterLectureCommand.CurriculumDTO(
                    split[i + 1],
                    null,
                    new ArrayList<>()
                );
                curriculumList.add(curriculumDTO);
                i += 5;
                sectionDTO = null;
                continue;
            } catch (Exception ignored) {
            }

            // section
            if (StringUtils.isBlank(split[i + 1])) {
                sectionDTO = new RegisterLectureCommand.SectionDTO(s, new ArrayList<>());
                curriculumDTO.sectionList().add(sectionDTO);
                i += 2;
                continue;
            } else if (sectionDTO == null) {
                sectionDTO = new RegisterLectureCommand.SectionDTO(s, new ArrayList<>());
                curriculumDTO.sectionList().add(sectionDTO);
            }

            // study
            sectionDTO.studyList().add(new RegisterLectureCommand.StudyDTO(s, DateTimeUtil.parseTime(split[i + 1])));
            i++;
        }

        return new RegisterLectureCommand(
            request.lectureName(),
            LecturePlatform.FASTCAMPUS,
            request.lecturePath(),
            curriculumList
        );
    }
}
