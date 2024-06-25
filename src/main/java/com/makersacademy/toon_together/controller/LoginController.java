package com.makersacademy.toon_together.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @GetMapping("/login")
    public String login(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        String logoutMessage = (String) session.getAttribute("logoutMessage");
//        String signupSuccessMessage = (String) session.getAttribute("signupSuccessMessage");

        if (logoutMessage != null) {
            model.addAttribute("logoutMessage", logoutMessage);
            session.removeAttribute("logoutMessage");
        }

//        if (signupSuccessMessage != null) {
//            model.addAttribute("signupSuccessMessage", signupSuccessMessage);
//            session.removeAttribute("signupSuccessMessage");
//            logger.info("Signup success message retrieved from session: " + signupSuccessMessage);
//        }

        return "login";
    }

    @GetMapping("/logout")
    public String logout() {
        return "logout";
    }
}
