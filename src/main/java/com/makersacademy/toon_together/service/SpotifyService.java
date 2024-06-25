package com.makersacademy.toon_together.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.Track;
import se.michaelthelin.spotify.requests.data.tracks.GetTrackRequest;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

@Service
public class SpotifyService {

    private final SpotifyApi spotifyApi;

    @Autowired
    public SpotifyService(SpotifyApi spotifyApi) {
        this.spotifyApi = spotifyApi;
    }

    public Track getTrack_Sync(String trackId) throws IOException, SpotifyWebApiException, ParseException {
        GetTrackRequest getTrackRequest = spotifyApi.getTrack(trackId).build();
        return getTrackRequest.execute();
    }

    public CompletableFuture<Track> getTrack_Async(String trackId) {
        GetTrackRequest getTrackRequest = spotifyApi.getTrack(trackId).build();
        return getTrackRequest.executeAsync();
    }
}