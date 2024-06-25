package com.makersacademy.toon_together.model;

import com.makersacademy.toon_together.model.Collaborator;
import com.makersacademy.toon_together.model.Playlist;
import com.makersacademy.toon_together.model.User;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CollaboratorTest {

    private User user;
    private Playlist playlist;

    @Before
    public void setUp() {
        user = new User();
        playlist = new Playlist();
    }

    @Test
    public void testCollaboratorConstructorWithoutDeleter() {
        Collaborator collaborator = new Collaborator(user, playlist);
        assertEquals(user, collaborator.getUser());
        assertEquals(playlist, collaborator.getPlaylist());
        assertFalse(collaborator.isDeleter());
    }

    @Test
    public void testCollaboratorConstructorWithDeleter() {
        Collaborator collaborator = new Collaborator(user, playlist, true);
        assertEquals(user, collaborator.getUser());
        assertEquals(playlist, collaborator.getPlaylist());
        assertTrue(collaborator.isDeleter());
    }


}
