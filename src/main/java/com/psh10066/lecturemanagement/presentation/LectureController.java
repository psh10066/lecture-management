package com.psh10066.lecturemanagement.presentation;

import com.psh10066.lecturemanagement.application.LectureService;
import com.psh10066.lecturemanagement.presentation.dto.RegisterLectureRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/lecture")
public class LectureController {

    private final LectureService lectureService;

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("request", RegisterLectureRequest.noArgs());
        return "lecture/register";
    }

    @PostMapping("/register")
    public String register(Model model, @Validated @ModelAttribute("request") RegisterLectureRequest request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("request", request);
            return "lecture/register";
        }
        try {
            lectureService.registerLecture(request);
        } catch (Exception e) {
            log.error("강의 등록 실패", e);
            bindingResult.reject("registerError", "강의 등록에 실패했습니다.");
            model.addAttribute("request", request);
            return "lecture/register";
        }
        return "redirect:/study";
    }
}
