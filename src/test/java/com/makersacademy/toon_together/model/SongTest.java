package com.makersacademy.toon_together.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.sql.Timestamp;

public class SongTest {

    public Song song;

    @Before
    public void setUp() {
        song = new Song();
        song.setCreatedAt(new Timestamp(10000));
    }

    @Test
    public void songHasCreatedAt() {
        assertEquals(song.getCreatedAt(), new Timestamp(10000));
    }
    @Test
    public void testDefaultConstructor() {
        Song song = new Song();
        assertNotNull(song);
    }

    @Test
    public void testParameterizedConstructor() {
        Song song = new Song("1", "Track Name", "Track Artist", "Album Name", "2023", "image.jpg");
        assertEquals("1", song.getId());
        assertEquals("Track Name", song.getTrackName());
        assertEquals("Track Artist", song.getTrackArtist());
        assertEquals("Album Name", song.getAlbum());
        assertEquals("2023", song.getReleaseYear());
        assertEquals("image.jpg", song.getImage());
        assertNotNull(song.getCreatedAt());
    }

    @Test
    public void testSetAndGetId() {
        song.setId("2");
        assertEquals("2", song.getId());
    }

    @Test
    public void testSetAndGetTrackName() {
        song.setTrackName("New Track Name");
        assertEquals("New Track Name", song.getTrackName());
    }

    @Test
    public void testSetAndGetTrackArtist() {
        song.setTrackArtist("New Track Artist");
        assertEquals("New Track Artist", song.getTrackArtist());
    }

    @Test
    public void testSetAndGetAlbum() {
        song.setAlbum("New Album Name");
        assertEquals("New Album Name", song.getAlbum());
    }

    @Test
    public void testSetAndGetReleaseYear() {
        song.setReleaseYear("2024");
        assertEquals("2024", song.getReleaseYear());
    }

    @Test
    public void testSetAndGetImage() {
        song.setImage("newimage.jpg");
        assertEquals("newimage.jpg", song.getImage());
    }

    @Test
    public void testCreatedAtIsNotNull() {
        assertNotNull(song.getCreatedAt());
    }
}
