package com.makersacademy.toon_together.controller;

import com.makersacademy.toon_together.model.Playlist;
import com.makersacademy.toon_together.model.User;
import com.makersacademy.toon_together.repository.AuthoritiesRepository;
import com.makersacademy.toon_together.repository.PlaylistRepository;
import com.makersacademy.toon_together.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
    public String index(@RequestParam(value = "search", required = false) String search, Model model, Authentication auth) {
        User user = userRepository.findByUsername(auth.getName());
        Iterable<Playlist> playlists;

        if (search != null && !search.isEmpty()) {
            playlists = playlistRepository.findByNameContainingIgnoreCase(search);
        } else {
            playlists = playlistRepository.findAll();
        }

        model.addAttribute("playlist", new Playlist());
        model.addAttribute("playlists", playlists);
        model.addAttribute("currentUser", user);
        return "/index";
    }

    @PostMapping("/playlists")
    public RedirectView create(@ModelAttribute Playlist playlist, Authentication auth) {
        playlist.setOwner(userRepository.findByUsername(auth.getName()));
        playlistRepository.save(playlist);
        return new RedirectView("/playlists");
    }

    @DeleteMapping("/playlists")
    public RedirectView delete(@RequestParam int id, Authentication auth) {
        User user = userRepository.findByUsername(auth.getName());
        Playlist playlist = playlistRepository.findById(id);
        if (user.getId() == playlist.getOwner().getId()) {
            playlistRepository.deleteById(id);
        }
        return new RedirectView("/playlists");
    }
}
