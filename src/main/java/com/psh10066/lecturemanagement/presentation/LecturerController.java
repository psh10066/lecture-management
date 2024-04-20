package com.psh10066.lecturemanagement.presentation;

import com.psh10066.lecturemanagement.application.LecturerService;
import com.psh10066.lecturemanagement.domain.user.User;
import com.psh10066.lecturemanagement.presentation.dto.RegisterLecturerRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
@RequestMapping("/lecturer")
public class LecturerController {

    private final LecturerService lecturerService;

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("request", RegisterLecturerRequest.noArgs());
        return "lecturer/register";
    }

    @PostMapping("/register")
    public String register(Model model, @AuthenticationPrincipal User user, @Validated @ModelAttribute("request") RegisterLecturerRequest request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("request", request);
            return "lecturer/register";
        }
        lecturerService.registerLecturer(user, request);
        return "redirect:/study";
    }
}
