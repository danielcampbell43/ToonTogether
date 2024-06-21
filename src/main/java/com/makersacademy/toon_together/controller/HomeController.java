package com.makersacademy.toon_together.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class HomeController {
	@RequestMapping(value = "/")
	public RedirectView index() {
		return new RedirectView("/playlists");
	}

	@GetMapping("/termsandconditions")
	public String showTermsAndConditions() {
		return "termsandconditions";
	}
}
