package com.makersacademy.toon_together.controller;

import com.makersacademy.toon_together.model.Favourite;
import com.makersacademy.toon_together.model.Playlist;
import com.makersacademy.toon_together.model.User;
import com.makersacademy.toon_together.repository.AuthoritiesRepository;
import com.makersacademy.toon_together.repository.FavouriteRepository;
import com.makersacademy.toon_together.repository.PlaylistRepository;
import com.makersacademy.toon_together.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Optional;

@Controller
public class PlaylistController {
    @Autowired
    PlaylistRepository playlistRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    FavouriteRepository favouriteRepository;


    @GetMapping("/playlists")
    public String index(@RequestParam(value = "search", required = false) String search, Model model, Authentication auth,
                        @RequestParam(defaultValue = "0") int page,
                        @RequestParam(defaultValue = "10") int size) {
        User user = userRepository.findByUsername(auth.getName());
        Pageable pageable = PageRequest.of(page, size);
        Page<Playlist> playlistPage;

        if (search != null && !search.isEmpty()) {
            playlistPage = playlistRepository.findByNameContainingIgnoreCase(search, pageable);
            model.addAttribute("searchQuery", search);
        } else {
            playlistPage = playlistRepository.findAllByOrderByCreatedAtDesc(pageable);
            model.addAttribute("searchQuery", null);
        }

        model.addAttribute("playlist", new Playlist());
        model.addAttribute("playlists", playlistPage.getContent());
        model.addAttribute("currentUser", user);
        model.addAttribute("totalPages", playlistPage.getTotalPages());
        model.addAttribute("currentPage", page);

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

    @PostMapping("/playlist/favourite")
    public RedirectView favouritePlaylist(@RequestParam int id, Authentication auth) {
        User user = userRepository.findByUsername(auth.getName());
        Playlist playlist = playlistRepository.findById(id);
        Optional<Favourite> playlistFavourite = Optional.ofNullable(favouriteRepository.findFavouriteByUserIdAndPlaylistId(user.getId(), playlist.getId()));
        System.out.println(playlistFavourite);
        if (playlistFavourite.isPresent()) {
            favouriteRepository.delete(playlistFavourite.get());

        }
        else {
            favouriteRepository.save(new Favourite(playlist, user));
        }
        return new RedirectView("/playlists");
    }
}
