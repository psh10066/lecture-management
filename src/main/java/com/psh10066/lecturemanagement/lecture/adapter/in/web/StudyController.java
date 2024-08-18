package com.psh10066.lecturemanagement.lecture.adapter.in.web;

import com.psh10066.lecturemanagement.jpaclient.paging.PagingMaker;
import com.psh10066.lecturemanagement.lecture.adapter.in.web.request.mapper.StudyRequestMapper;
import com.psh10066.lecturemanagement.lecture.application.port.in.command.StudiesCommand;
import com.psh10066.lecturemanagement.lecture.application.port.in.dto.StudyListDTO;
import com.psh10066.lecturemanagement.lecture.adapter.in.web.request.StudiesRequest;
import com.psh10066.lecturemanagement.lecture.application.port.in.LectureService;
import com.psh10066.lecturemanagement.lecture.application.port.in.StudyService;
import com.psh10066.lecturemanagement.lecture.domain.LecturePlatform;
import com.psh10066.lecturemanagement.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/study")
public class StudyController {

    private final PagingMaker pagingMaker;
    private final LectureService lectureService;
    private final StudyService studyService;

    @GetMapping
    public String studies(Model model, @AuthenticationPrincipal User user, Pageable pageable, StudiesRequest request) {
        model.addAttribute("request", request);
        model.addAttribute("lecturePlatforms", LecturePlatform.values());
        model.addAttribute("lectures", lectureService.lectureList(user));

        StudiesCommand command = StudyRequestMapper.INSTANCE.toCommand(request);
        Page<StudyListDTO> studyList = studyService.studyList(user, pageable, command);
        model.addAttribute("paging", pagingMaker.getPaging(pageable, studyList));
        model.addAttribute("studies", studyList);
        return "study/list";
    }
}
