package com.psh10066.lecturemanagement.lecture.adapter.in.web.template;

import com.psh10066.lecturemanagement.lecture.application.port.in.LectureService;
import com.psh10066.lecturemanagement.lecture.application.port.in.command.RegisterLectureCommand;
import com.psh10066.lecturemanagement.lecture.domain.LecturePlatform;
import com.psh10066.lecturemanagement.user.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@RequiredArgsConstructor
public abstract class RegisterLectureViewTemplate {

    private final LecturePlatform lecturePlatform;
    private final User user;
    private final Object request;
    private final BindingResult bindingResult;
    private final LectureService lectureService;

    public ModelAndView view() {
        if (bindingResult.hasErrors()) {
            return registerForm(lecturePlatform, request);
        }
        Long lectureId;
        try {
            lectureId = lectureService.registerLecture(user, this.command()).lectureId();
        } catch (Exception e) {
            log.error("강의 등록 실패", e);
            bindingResult.reject("registerError", "강의 등록에 실패했습니다.");
            return registerForm(lecturePlatform, request);
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/lecture/" + lectureId);
        return modelAndView;
    }

    public abstract RegisterLectureCommand command();

    public static ModelAndView registerForm(LecturePlatform lecturePlatform, Object request) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("request", request);
        modelAndView.addObject("lecturePlatforms", LecturePlatform.values());
        modelAndView.addObject("lecturePlatform", lecturePlatform);
        modelAndView.setViewName("lecture/register");
        return modelAndView;
    }
}
