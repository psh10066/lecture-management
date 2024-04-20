package com.psh10066.lecturemanagement.presentation;

import com.psh10066.lecturemanagement.application.CurriculumService;
import com.psh10066.lecturemanagement.application.LectureService;
import com.psh10066.lecturemanagement.application.LecturerService;
import com.psh10066.lecturemanagement.domain.user.User;
import com.psh10066.lecturemanagement.infrastructure.paging.PagingMaker;
import com.psh10066.lecturemanagement.presentation.dto.CurriculumListDTO;
import com.psh10066.lecturemanagement.presentation.dto.CurriculumsRequest;
import com.psh10066.lecturemanagement.presentation.dto.ModifyCurriculumRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

    private final PagingMaker pagingMaker;
    private final LectureService lectureService;
    private final LecturerService lecturerService;
    private final CurriculumService curriculumService;

    @GetMapping
    public String curriculums(Model model, @AuthenticationPrincipal User user, Pageable pageable, CurriculumsRequest request) {
        model.addAttribute("request", request);
        model.addAttribute("lectures", lectureService.lectureList(user));

        Page<CurriculumListDTO> curriculumList = curriculumService.curriculumList(user, pageable, request);
        model.addAttribute("paging", pagingMaker.getPaging(pageable, curriculumList));
        model.addAttribute("curriculums", curriculumList.getContent());
        return "curriculum/list";
    }

    @GetMapping("/{curriculumId}/modify")
    public String modify(Model model, @AuthenticationPrincipal User user, @PathVariable Long curriculumId) {
        model.addAttribute("lecturers", lecturerService.lecturerList(user));
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
