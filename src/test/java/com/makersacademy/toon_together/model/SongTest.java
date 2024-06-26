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
}
