package com.makersacademy.toon_together.dto;

import com.makersacademy.toon_together.model.Playlist;
import com.makersacademy.toon_together.model.Song;

import java.util.List;

public class PlaylistWithSongsDTO {
    private Playlist playlist;
    private List<Song> songs;

    // Constructors, getters, and setters
    public PlaylistWithSongsDTO() {
    }

    public PlaylistWithSongsDTO(Playlist playlist, List<Song> songs) {
        this.playlist = playlist;
        this.songs = songs;
    }

    public Playlist getPlaylist() {
        return playlist;
    }

    public void setPlaylist(Playlist playlist) {
        this.playlist = playlist;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }
}

