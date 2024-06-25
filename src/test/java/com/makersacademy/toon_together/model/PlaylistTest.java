package com.makersacademy.toon_together.model;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.makersacademy.toon_together.controller.HomeController;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

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
