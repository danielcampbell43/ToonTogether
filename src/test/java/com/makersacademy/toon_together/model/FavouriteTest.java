package com.makersacademy.toon_together.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.sql.Timestamp;

public class FavouriteTest {

    private User user;
    private Playlist playlist;
    private Favourite favourite;

    @Before
    public void setUp() {
        user = new User();
        user.setId(1);
        playlist = new Playlist();
        playlist.setId(1);
        favourite = new Favourite(playlist, user);
        favourite.setCreatedAt(new Timestamp(10000));
    }

    @Test
    public void testDefaultConstructor() {
        Favourite favourite = new Favourite();
        assertNotNull(favourite);
    }

    @Test
    public void testParameterizedConstructor() {
        Favourite favourite = new Favourite(playlist, user);
        assertEquals(user, favourite.getUser());
        assertEquals(playlist, favourite.getPlaylist());
    }

    @Test
    public void testGetUser() {
        User newUser = new User();
        newUser.setId(2);
        favourite.setUser(newUser);
        assertEquals(newUser, favourite.getUser());
    }

    @Test
    public void testGetPlaylist() {
        Playlist newPlaylist = new Playlist();
        newPlaylist.setId(2);
        favourite.setPlaylist(newPlaylist);
        assertEquals(newPlaylist, favourite.getPlaylist());
    }

    @Test
    public void testGetCreatedAt() {
        Timestamp newTimestamp = new Timestamp(20000);
        favourite.setCreatedAt(newTimestamp);
        assertEquals(newTimestamp, favourite.getCreatedAt());
    }
}
