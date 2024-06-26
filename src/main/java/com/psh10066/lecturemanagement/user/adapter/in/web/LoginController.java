package com.psh10066.lecturemanagement.user.adapter.in.web;

import com.psh10066.lecturemanagement.user.adapter.in.web.request.LoginResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {

    @GetMapping
    public String login(Model model, LoginResult result) {
        model.addAttribute("result", result);
        return "login/index";
    }
}
