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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Controller
public class UserProfileController {
    @Autowired
    PlaylistRepository playlistRepository;
    @Autowired
    UserRepository userRepository;


    @RequestMapping(value = "/myprofile")
    public String myProfile(Model model, Authentication authentication) {
        if (authentication != null) {
            String username = authentication.getName();
            User user = userRepository.findByUsername(username);
            Iterable<Playlist> playlists = playlistRepository.findByOwner(userRepository.findByUsername(username));
            model.addAttribute("playlists", playlists);
            model.addAttribute("user", user);
        } else {
            model.addAttribute("playlists", null);
        }
        return "my-profile";
    }

    @DeleteMapping("/myprofile")
    public RedirectView delete(@RequestParam int id, Authentication auth) {
        User user = userRepository.findByUsername(auth.getName());
        Playlist playlist = playlistRepository.findById(id);
        if (user.getId() == playlist.getOwner().getId()) {
            playlistRepository.deleteById(id);
        }
//        maybe want to add some sort of pop-up saying you can delete a playlist that isn't yours
        return new RedirectView("/myprofile");
    }

    @PostMapping("/myprofile/updateProfilePicture")
    public RedirectView updateProfilePicture(@RequestParam("profilePicture") String profilePictureUrl, Authentication auth) {
        User user = userRepository.findByUsername(auth.getName());
        if (user != null) {
            user.setProfilePicture(profilePictureUrl);
            userRepository.save(user);
        }
        return new RedirectView("/myprofile");
    }
}
