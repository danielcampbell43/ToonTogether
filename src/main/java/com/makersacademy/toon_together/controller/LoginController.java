package com.makersacademy.toon_together.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/logout")
    public String logout() {
        return "logout"; // Redirect to confirmation page
    }

    @PostMapping("/logout") // Handle POST request for logout
    public String logoutPage() {
        // Spring Security will handle the actual logout process
        return "redirect:/login?logout"; // Redirect to login page with logout parameter
    }
}
