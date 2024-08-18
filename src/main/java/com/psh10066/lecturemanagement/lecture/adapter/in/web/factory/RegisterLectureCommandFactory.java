package com.psh10066.lecturemanagement.lecture.adapter.in.web.factory;

import com.psh10066.lecturemanagement.core.util.DateTimeUtil;
import com.psh10066.lecturemanagement.lecture.adapter.in.web.request.RegisterFastcampusLectureRequest;
import com.psh10066.lecturemanagement.lecture.adapter.out.html.HtmlClient;
import com.psh10066.lecturemanagement.lecture.application.port.in.command.RegisterLectureCommand;
import com.psh10066.lecturemanagement.lecture.domain.LecturePlatform;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class RegisterLectureCommandFactory {

    private final HtmlClient htmlClient;

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

    public RegisterLectureCommand fromInflearnUrl(String url) {
        final String accordionClass = "mantine-Accordion-item";
        final String titleClass = "mantine-Title-root";
        final String textClass = "mantine-Text-root";

        String prefix = "https://www.inflearn.com/course/";
        if (!url.startsWith(prefix)) {
            throw new IllegalArgumentException("잘못된 강의 경로입니다.");
        }

        String lecturePath = URLDecoder.decode(
            prefix +
                url.substring(prefix.length())
                    .split("/")[0]
                    .split("\\?")[0]
                    .split("#")[0],
            StandardCharsets.UTF_8
        );

        Document document = htmlClient.connect(lecturePath);

        lecturePath += "/dashboard";

        Elements sections = document.getElementsByClass(accordionClass);
        if (sections.isEmpty()) {
            throw new IllegalArgumentException("잘못된 강의 경로입니다.");
        }

        String lectureName = document.getElementsByClass(titleClass).getFirst().ownText();
        String lecturerName = document
            .getElementsByTag("section").getFirst()
            .getElementsByClass(textClass).getLast().text();

        RegisterLectureCommand.CurriculumDTO curriculumDTO = new RegisterLectureCommand.CurriculumDTO(lectureName, lecturerName, new ArrayList<>());

        for (Element sectionElement : sections) {
            String sectionName = sectionElement.getElementsByClass(textClass).getFirst().text();

            RegisterLectureCommand.SectionDTO sectionDTO = new RegisterLectureCommand.SectionDTO(sectionName, new ArrayList<>());
            curriculumDTO.sectionList().add(sectionDTO);

            Elements studies = sectionElement.getElementsByTag("li");
            for (Element studyElement : studies) {
                Elements studyTextElements = studyElement.getElementsByTag("p");
                String studyName = studyTextElements.getFirst().text();
                String studyTime = studyTextElements.size() > 1 ? studyTextElements.get(1).text() : null;

                RegisterLectureCommand.StudyDTO studyDTO = new RegisterLectureCommand.StudyDTO(studyName, DateTimeUtil.parseTime(studyTime));
                sectionDTO.studyList().add(studyDTO);
            }
        }

        return new RegisterLectureCommand(
            lectureName,
            LecturePlatform.INFLEARN,
            lecturePath,
            List.of(curriculumDTO)
        );
    }
}
