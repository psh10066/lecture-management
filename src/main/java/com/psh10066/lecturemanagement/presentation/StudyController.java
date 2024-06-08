package com.psh10066.lecturemanagement.presentation;

import com.psh10066.lecturemanagement.application.LectureService;
import com.psh10066.lecturemanagement.application.StudyService;
import com.psh10066.lecturemanagement.domain.lecture.type.LecturePlatform;
import com.psh10066.lecturemanagement.user.domain.User;
import com.psh10066.lecturemanagement.infrastructure.paging.PagingMaker;
import com.psh10066.lecturemanagement.presentation.dto.StudiesRequest;
import com.psh10066.lecturemanagement.presentation.dto.StudyListDTO;
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

        Page<StudyListDTO> studyList = studyService.studyList(user, pageable, request);
        model.addAttribute("paging", pagingMaker.getPaging(pageable, studyList));
        model.addAttribute("studies", studyList);
        return "study/list";
    }
}
