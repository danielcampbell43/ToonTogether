package com.makersacademy.toon_together.model;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.sql.Timestamp;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PlaylistSongTest {
    public Playlist playlist;
    public Song song;
    public PlaylistSong playlistSong;

    @Before
    public void setUp() {
        playlist = mock(Playlist.class);
        when(playlist.getId()).thenReturn(1);
        song = mock(Song.class);
//        when(song.getId()).thenReturn("abc");
//        playlistSong = new PlaylistSong(playlist, song, new Timestamp(10000));
    }

    @Test
    public void playlistSongHasPlaylist() {
        assertEquals(playlistSong.getPlaylist().getId(), 1);
    }

//    @Test
//    public void playlistSongHasSong() {
//        assertEquals(playlistSong.getSong().getId(), "abc");
//    }

    @Test
    public void playlistSongHasCreateedAt() {
        assertEquals(playlistSong.getCreatedAt(), new Timestamp(10000));
    }
}
