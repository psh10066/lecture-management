package com.psh10066.lecturemanagement.presentation;

import com.psh10066.lecturemanagement.application.LectureService;
import com.psh10066.lecturemanagement.application.StudyService;
import com.psh10066.lecturemanagement.presentation.dto.StudiesRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/study")
public class StudyController {

    private final LectureService lectureService;
    private final StudyService studyService;

    @GetMapping
    public String studies(Model model, StudiesRequest request) {
        model.addAttribute("request", request);
        model.addAttribute("lectures", lectureService.lectureList());
        model.addAttribute("studies", studyService.studyList(request));
        return "study/list";
    }
}
