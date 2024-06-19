package com.makersacademy.toon_together.model;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import java.sql.Timestamp;
public class PlaylistTest {

    public User user;
    public Playlist playlist;

    @Before
    public void setUp() {
        user = mock(User.class);
        when(user.getUsername()).thenReturn("ant and dec");
        playlist = new Playlist(user, "lets get ready to rumble", new Timestamp(10000));
    }

    @Test
    public void playlistHasUser() {
        assertEquals("ant and dec", playlist.getOwner().getUsername());
    }

    @Test
    public void playlistHasName() {
        assertEquals("lets get ready to rumble", playlist.getName());
    }

    @Test
    public void playlistHasCreatedAt() {
        assertEquals(new Timestamp(10000), playlist.getCreatedAt());
    }
}
