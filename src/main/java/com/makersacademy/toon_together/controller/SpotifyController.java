package com.makersacademy.toon_together.controller;

import com.makersacademy.toon_together.service.SpotifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
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

    @GetMapping("/track/sync/{id}")
    public Track getTrackSync(@PathVariable String id) throws IOException, SpotifyWebApiException, ParseException {
        return spotifyService.getTrack_Sync(id);
    }

    @GetMapping("/track/async/{id}")
    public CompletableFuture<Track> getTrackAsync(@PathVariable String id) {
        return spotifyService.getTrack_Async(id);
    }
}