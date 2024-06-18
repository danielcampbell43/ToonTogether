package com.makersacademy.toon_together.controller;

import com.makersacademy.toon_together.model.Playlist;
import com.makersacademy.toon_together.repository.AuthoritiesRepository;
import com.makersacademy.toon_together.repository.PlaylistRepository;
import com.makersacademy.toon_together.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PlaylistController {
    @Autowired
    PlaylistRepository playlistRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    AuthoritiesRepository authoritiesRepository;

    @GetMapping("/playlists")
    public String index(Model model) {

        Iterable<Playlist> playlists = playlistRepository.findAll();
        model.addAttribute("playlists", playlists);
        return "/index";
    }
}
