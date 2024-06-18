package com.makersacademy.toon_together.controller;

import com.makersacademy.toon_together.model.Playlist;
import com.makersacademy.toon_together.model.User;
import com.makersacademy.toon_together.repository.AuthoritiesRepository;
import com.makersacademy.toon_together.repository.PlaylistRepository;
import com.makersacademy.toon_together.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
public class UserProfileController {
    @Autowired
    PlaylistRepository playlistRepository;


    @RequestMapping(value = "/myprofile")
    public String myProfile(Model model, Authentication authentication) {
        if (authentication != null) {
            String username = authentication.getName();
            Iterable<Playlist> playlists = playlistRepository.findByUserUsername(username);
            model.addAttribute("playlists", playlists);
        } else {
            model.addAttribute("playlists", null);
        }
        return "my-profile";
    }

}
