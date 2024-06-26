package com.makersacademy.toon_together.controller;

import com.makersacademy.toon_together.model.Playlist;
import com.makersacademy.toon_together.model.PlaylistSong;
import com.makersacademy.toon_together.model.Song;
import com.makersacademy.toon_together.repository.PlaylistRepository;
import com.makersacademy.toon_together.repository.PlaylistSongsRepository;
import com.makersacademy.toon_together.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;

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

    @GetMapping("/myprofile/playlist/{id}")
    public String getPlaylistById(@PathVariable("id") Long id, Model model) {
        // Convert Long id to Integer if necessary
        Integer playlistId = Integer.valueOf(id.intValue());

        Playlist playlist = playlistRepository.findById(playlistId)
                .orElseThrow(() -> new IllegalArgumentException("Playlist not found with id: " + id));

        List<PlaylistSong> playlistSongs = playlistSongsRepository.findByPlaylistId(playlistId);

        List<String> songIds = playlistSongs.stream()
                .map(ps -> ps.getSongId())  // Assuming getSongId returns a String
                .collect(Collectors.toList());

        Iterable<Song> songs = songRepository.findAllById(songIds);  // Use Iterable<String>

        System.out.println(songs);

        model.addAttribute("playlist", playlist);
        model.addAttribute("songs", songs);
        return "playlist";
    }
}


