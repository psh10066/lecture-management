package com.psh10066.lecturemanagement.presentation;

import com.psh10066.lecturemanagement.application.CurriculumService;
import com.psh10066.lecturemanagement.application.LectureService;
import com.psh10066.lecturemanagement.application.LecturerService;
import com.psh10066.lecturemanagement.presentation.dto.CurriculumsRequest;
import com.psh10066.lecturemanagement.presentation.dto.ModifyCurriculumRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/curriculum")
public class CurriculumController {

    private final LectureService lectureService;
    private final LecturerService lecturerService;
    private final CurriculumService curriculumService;

    @GetMapping
    public String curriculums(Model model, CurriculumsRequest request) {
        model.addAttribute("request", request);
        model.addAttribute("lectures", lectureService.lectureList());
        model.addAttribute("curriculums", curriculumService.curriculumList(request));
        return "curriculum/list";
    }

    @GetMapping("/{curriculumId}/modify")
    public String modify(Model model, @PathVariable Long curriculumId) {
        model.addAttribute("lecturers", lecturerService.lecturerList());
        model.addAttribute("curriculum", curriculumService.curriculumInfo(curriculumId));
        return "curriculum/modify";
    }

    @PostMapping("/{curriculumId}/modify")
    public String modify(Model model, ModifyCurriculumRequest request, @PathVariable Long curriculumId) {
        model.addAttribute("request", request);
        curriculumService.modifyCurriculum(curriculumId, request);
        return "redirect:/curriculum";
    }
}
