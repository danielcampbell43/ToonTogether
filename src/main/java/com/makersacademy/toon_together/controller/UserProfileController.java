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
    @Autowired
    UserRepository userRepository;
    @Autowired
    AuthoritiesRepository authoritiesRepository;

    @RequestMapping(value = "/myprofile")
    public String myProfile(Model model, Authentication authentication) {
        if (authentication == null) {
            // Handle scenario where userDetails is null (authentication failed or not authenticated)
            return "redirect:/login"; // Example: Redirect to login page
        }
        String user = authentication.getName();
        Iterable<Playlist> playlists = playlistRepository.findByUser(user);
//        Optional<User> user = userRepository.findByUsername(userDetails.getUsername());
//        Iterable<Playlist> playlists = playlistRepository.findByUser(user);
        model.addAttribute("playlists", playlists);
        return "/my-profile";
    }
}



//@Controller
//public class UserProfileController {
//    @Autowired PlaylistRepository playlistRepository;
//    @Autowired UserRepository userRepository;
//
//    @RequestMapping(value = "/myprofile")
//    public String myProfile(Model model, Authentication authentication) {
//        // Fetch playlists for the logged-in user
//        String username = authentication.getName();
//        Iterable<Playlist> playlists = playlistRepository.findByUserUsername(username);
//        model.addAttribute("playlists", playlists);
//        return "my-profile";
//    }
//}
