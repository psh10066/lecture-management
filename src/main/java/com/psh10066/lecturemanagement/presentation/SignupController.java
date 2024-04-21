package com.psh10066.lecturemanagement.presentation;

import com.psh10066.lecturemanagement.application.UserService;
import com.psh10066.lecturemanagement.presentation.dto.SignupRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/signup")
@RequiredArgsConstructor
public class SignupController {

    private final UserService userService;

    @GetMapping
    public String signup(Model model) {
        model.addAttribute("request", SignupRequest.noArgs());
        return "signup/index";
    }

    @PostMapping
    public String signup(Model model, @Validated @ModelAttribute("request") SignupRequest request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("request", request);
            return "signup/index";
        }
        if (userService.existsByUsername(request.username())) {
            bindingResult.reject("usernameError", "이미 존재하는 아이디입니다.");
            model.addAttribute("request", request);
            return "signup/index";
        }
        userService.save(request.username(), request.password());

        return "redirect:/login";
    }
}
