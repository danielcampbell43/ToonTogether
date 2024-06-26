package com.makersacademy.toon_together.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class UserTest {

    public User user;

    @Before
    public void setUp() {
        user = new User("barry manilow", "copacabana",
                true, "pic.jpg", new Timestamp(10000));
    }

    @Test
    public void userHasUsername() {
        assertEquals(user.getUsername(), "barry manilow");
    }

    @Test
    public void userHasPassword() {
        assertEquals(user.getPassword(), "copacabana");
    }

    @Test
    public void userIsEnabled() {
        assert(user.isEnabled());
    }

    @Test
    public void userHasProfilePicture() {
        assertEquals(user.getProfilePicture(), "pic.jpg");
    }

    @Test
    public void userHasCreatedAt() {
        assertEquals(user.getCreatedAt(), new Timestamp(10000));
    }

    @Test
    public void testGetFavouritePlaylists() {
        // checks favourite playlists are successfully added to favourites
        List<Favourite> favourites = new ArrayList<>();
        Playlist playlist1 = new Playlist();
        playlist1.setId(1);
        Playlist playlist2 = new Playlist();
        playlist2.setId(2);
        favourites.add(new Favourite(playlist1, user));
        favourites.add(new Favourite(playlist2, user));

        user.setUserFavourites(favourites);

        List<Integer> favouriteIds = user.getFavouritePlaylists();
        assertEquals(2, favouriteIds.size());
        assertTrue(favouriteIds.contains(1));
        assertTrue(favouriteIds.contains(2));
    }
}
