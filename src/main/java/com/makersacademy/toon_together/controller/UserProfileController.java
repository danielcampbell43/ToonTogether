package com.makersacademy.toon_together.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserProfileController {
    @RequestMapping(value = "/myprofile")
    public String myProfile() {
        return "/my-profile";}
}


