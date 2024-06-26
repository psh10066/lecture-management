package com.psh10066.lecturemanagement.lecture.adapter.in.web;

import com.psh10066.lecturemanagement.lecture.adapter.in.web.dto.LectureInfoDTO;
import com.psh10066.lecturemanagement.lecture.adapter.in.web.dto.LectureListDTO;
import com.psh10066.lecturemanagement.lecture.adapter.in.web.dto.LectureModifyInfoDTO;
import com.psh10066.lecturemanagement.lecture.adapter.in.web.request.LecturesRequest;
import com.psh10066.lecturemanagement.lecture.adapter.in.web.request.ModifyLectureRequest;
import com.psh10066.lecturemanagement.lecture.adapter.in.web.request.RegisterFastcampusLectureRequest;
import com.psh10066.lecturemanagement.lecture.adapter.in.web.request.RegisterInflearnLectureRequest;
import com.psh10066.lecturemanagement.lecture.application.port.in.LectureService;
import com.psh10066.lecturemanagement.lecture.domain.LecturePlatform;
import com.psh10066.lecturemanagement.user.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/lecture")
public class LectureController {

    private final LectureService lectureService;

    @GetMapping
    public String lectures(Model model, @AuthenticationPrincipal User user, LecturesRequest request) {
        model.addAttribute("request", request);
        model.addAttribute("lecturePlatforms", LecturePlatform.values());

        List<LectureListDTO> lectureList = lectureService.lectureList(user, request);
        model.addAttribute("lectures", lectureList);
        return "lecture/list";
    }

    @GetMapping("/{lectureId}")
    public String lectureInfo(Model model, @PathVariable Long lectureId) {

        LectureInfoDTO lectureInfo = lectureService.lectureInfo(lectureId);
        model.addAttribute("lecture", lectureInfo);
        return "lecture/info";
    }

    @GetMapping("/{lectureId}/modify")
    public String lectureModify(Model model, @PathVariable Long lectureId) {

        LectureModifyInfoDTO lectureInfo = lectureService.lectureModifyInfo(lectureId);
        model.addAttribute("lecture", lectureInfo);
        return "lecture/modify";
    }

    @PostMapping("/{lectureId}/modify")
    public String lectureModify(@AuthenticationPrincipal User user, @PathVariable Long lectureId, @ModelAttribute("request") ModifyLectureRequest request) {
        lectureService.modifyLecture(user, lectureId, request);
        return "redirect:/lecture/" + lectureId;
    }

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
        Long lectureId;
        try {
            lectureId = lectureService.registerFastcampusLecture(user, request).getLectureId();
        } catch (Exception e) {
            log.error("강의 등록 실패", e);
            bindingResult.reject("registerError", "강의 등록에 실패했습니다.");
            return this.registerForm(LecturePlatform.FASTCAMPUS, request);
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/lecture/" + lectureId);
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
        Long lectureId;
        try {
            lectureId = lectureService.registerInflearnLecture(user, request).getLectureId();
        } catch (Exception e) {
            log.error("강의 등록 실패", e);
            bindingResult.reject("registerError", "강의 등록에 실패했습니다.");
            return this.registerForm(LecturePlatform.INFLEARN, request);
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/lecture/" + lectureId);
        return modelAndView;
    }
}
