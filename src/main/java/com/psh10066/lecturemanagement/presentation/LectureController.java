package com.psh10066.lecturemanagement.presentation;

import com.psh10066.lecturemanagement.application.LectureService;
import com.psh10066.lecturemanagement.domain.lecture.type.LecturePlatform;
import com.psh10066.lecturemanagement.domain.user.User;
import com.psh10066.lecturemanagement.presentation.dto.RegisterFastcampusLectureRequest;
import com.psh10066.lecturemanagement.presentation.dto.RegisterInflearnLectureRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/lecture")
public class LectureController {

    private final LectureService lectureService;

    private <T> ModelAndView registerForm(LecturePlatform lecturePlatform, T request) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("request", request);
        modelAndView.addObject("lecturePlatforms", LecturePlatform.values());
        modelAndView.addObject("lecturePlatform", lecturePlatform);
        modelAndView.setViewName("lecture/register");
        return modelAndView;
    }

    @GetMapping("/register/fastcampus")
    public ModelAndView registerFastcampus() {
        return this.registerForm(LecturePlatform.FASTCAMPUS, RegisterFastcampusLectureRequest.noArgs());
    }

    @PostMapping("/register/fastcampus")
    public ModelAndView registerFastcampus(@AuthenticationPrincipal User user, @Validated @ModelAttribute("request") RegisterFastcampusLectureRequest request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return this.registerForm(LecturePlatform.FASTCAMPUS, request);
        }
        try {
            lectureService.registerFastcampusLecture(user, request);
        } catch (Exception e) {
            log.error("강의 등록 실패", e);
            bindingResult.reject("registerError", "강의 등록에 실패했습니다.");
            return this.registerForm(LecturePlatform.FASTCAMPUS, request);
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/study");
        return modelAndView;
    }

    @GetMapping("/register/inflearn")
    public ModelAndView registerInflearn() {
        return this.registerForm(LecturePlatform.INFLEARN, RegisterInflearnLectureRequest.noArgs());
    }

    @PostMapping("/register/inflearn")
    public ModelAndView registerInflearn(@AuthenticationPrincipal User user, @Validated @ModelAttribute("request") RegisterInflearnLectureRequest request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return this.registerForm(LecturePlatform.INFLEARN, request);
        }
        try {
            lectureService.registerInflearnLecture(user, request);
        } catch (Exception e) {
            log.error("강의 등록 실패", e);
            bindingResult.reject("registerError", "강의 등록에 실패했습니다.");
            return this.registerForm(LecturePlatform.INFLEARN, request);
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/study");
        return modelAndView;
    }
}
