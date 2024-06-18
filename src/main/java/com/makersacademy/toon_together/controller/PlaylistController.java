package com.makersacademy.toon_together.controller;

import com.makersacademy.toon_together.model.Playlist;
import com.makersacademy.toon_together.repository.AuthoritiesRepository;
import com.makersacademy.toon_together.repository.PlaylistRepository;
import com.makersacademy.toon_together.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

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
        model.addAttribute("playlist", new Playlist());
        model.addAttribute("playlists", playlists);
        return "/index";
    }

    @PostMapping("/playlists")
    public RedirectView create(@ModelAttribute Playlist playlist, Authentication auth) {
        playlist.setUser(userRepository.findByUsername(auth.getName()));
        playlistRepository.save(playlist);
        return new RedirectView("/playlists");

    }
}
