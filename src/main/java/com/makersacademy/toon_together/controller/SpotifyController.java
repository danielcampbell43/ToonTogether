package com.makersacademy.toon_together.controller;

import com.makersacademy.toon_together.service.SpotifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import se.michaelthelin.spotify.model_objects.specification.ArtistSimplified;
import se.michaelthelin.spotify.model_objects.specification.Track;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

@RestController
public class SpotifyController {

    // http://localhost:8080/track/sync/6rqhFgbbKwnb9MLmUQDhG6

    @Autowired
    private SpotifyService spotifyService;

    // Handle form submission
    @PostMapping("/addSong")
    public String addSong(@RequestParam("songName") String songName, Model model) {
        // Call the Spotify service to get track data
        try {
            Track track = spotifyService.searchTrackByName(songName);
            model.addAttribute("track", track);
            System.out.println(track);
            StringBuilder artists = new StringBuilder();
            for (ArtistSimplified artist : track.getArtists()) {
                artists.append(artist.getName()).append(", ");
            }
            String artistNames = artists.substring(0, artists.length() - 2);
            // Remove the trailing comma and space
            System.out.println(track.getName());
            System.out.println(artistNames);
            // json
            // name, artist, album, release_year, image
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
