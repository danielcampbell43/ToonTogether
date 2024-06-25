package com.makersacademy.toon_together.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.makersacademy.toon_together.model.PlaylistSong;
import com.makersacademy.toon_together.model.Song;
import com.makersacademy.toon_together.repository.PlaylistSongsRepository;
import com.makersacademy.toon_together.repository.SongRepository;
import com.makersacademy.toon_together.service.SpotifyService;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.ArtistSimplified;
import se.michaelthelin.spotify.model_objects.specification.Track;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@RestController
public class SpotifyController {

    @Autowired
    private SpotifyService spotifyService;

    @Autowired
    private SongRepository songRepository;

    @Autowired
    private PlaylistSongsRepository playlistSongRepository;

    // Handle form submission
    @PostMapping("/addSong")
    public String addSong(@RequestParam("songName") String songName, @RequestParam("playlistId") String playlistId, Model model) {
        try {
            Track track = spotifyService.searchTrackByName(songName);
            model.addAttribute("track", track);

            StringBuilder artists = new StringBuilder();
            for (ArtistSimplified artist : track.getArtists()) {
                artists.append(artist.getName()).append(", ");
            }
            String artistNames = artists.substring(0, artists.length() - 2);

            // Create Song object with individual fields
            Song song = new Song(
                    track.getId(),
                    track.getName(),
                    artistNames,
                    track.getAlbum().getName(),
                    track.getAlbum().getReleaseDate(),
                    track.getAlbum().getImages()[0].getUrl()
            );

            System.out.println(playlistId); //Strings
            System.out.println(track.getId()); // Strings

            PlaylistSong playlistSong = new PlaylistSong(
                    Integer.parseInt(playlistId),
                    track.getId()
            );

            playlistSongRepository.save(playlistSong);


            songRepository.save(song);

            return "termsandconditions";
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            e.printStackTrace();
            model.addAttribute("error", "Failed to retrieve track data");
            return "error";
        }
    }

    @GetMapping("/track/sync/{id}")
    public Track getTrackSync(@PathVariable String id) throws IOException, SpotifyWebApiException, ParseException {
        return spotifyService.getTrack_Sync(id);
    }

    @GetMapping("/track/async/{id}")
    public CompletableFuture<Track> getTrackAsync(@PathVariable String id) {
        return spotifyService.getTrack_Async(id);
    }
}