package com.makersacademy.toon_together.controller;

import com.makersacademy.toon_together.dto.PlaylistWithSongsDTO;
import com.makersacademy.toon_together.model.*;
import com.makersacademy.toon_together.repository.PlaylistRepository;
import com.makersacademy.toon_together.repository.PlaylistSongsRepository;
import com.makersacademy.toon_together.repository.SongRepository;
import com.makersacademy.toon_together.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class PlaylistPageController {

    @Autowired
    private PlaylistRepository playlistRepository;

    @Autowired
    private PlaylistSongsRepository playlistSongsRepository;

    @Autowired
    private SongRepository songRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/myprofile/playlist/{id}")
    public String getPlaylistById(@PathVariable("id") Long id, Model model, Authentication auth) {
        // Convert Long id to Integer if necessary
        Integer playlistId = Integer.valueOf(id.intValue());
        boolean deleter = false;

        Playlist playlist = playlistRepository.findById(playlistId)
                .orElseThrow(() -> new IllegalArgumentException("Playlist not found with id: " + id));

        List<PlaylistSong> playlistSongs = playlistSongsRepository.findByPlaylistId(playlistId);

        for (int i : playlist.getDeleters()) {
            if (i == userRepository.findByUsername(auth.getName()).getId()) {
                deleter = true;

            }
        }

        List<String> songIds = playlistSongs.stream()
                .map(ps -> ps.getSongId())  // Assuming getSongId returns a String
                .collect(Collectors.toList());

        Iterable<Song> songs = songRepository.findAllById(songIds);  // Use Iterable<String>

        PlaylistWithSongsDTO playlistWithSongsDTO = new PlaylistWithSongsDTO(playlist, (List<Song>) songs);

        model.addAttribute("playlistWithSongs", playlistWithSongsDTO);
        model.addAttribute("deleter", deleter);
        return "playlist";
    }
}


