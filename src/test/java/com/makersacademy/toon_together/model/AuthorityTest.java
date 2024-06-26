package com.makersacademy.toon_together.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class AuthorityTest {

    private Authority authority;

    @Before
    public void setUp() {
        authority = new Authority("user1", "ROLE_USER");
    }

    @Test
    public void testDefaultConstructor() {
        Authority authority = new Authority();
        assertNotNull(authority);
    }

    @Test
    public void testParameterizedConstructor() {
        Authority authority = new Authority("user1", "ROLE_USER");
        assertEquals("user1", authority.getUsername());
        assertEquals("ROLE_USER", authority.getAuthority());
    }

    @Test
    public void testGetId() {
        authority.setId(1);
        assertEquals(1, authority.getId());
    }

    @Test
    public void testGetUsername() {
        authority.setUsername("user2");
        assertEquals("user2", authority.getUsername());
    }

    @Test
    public void testGetAuthority() {
        authority.setAuthority("ROLE_ADMIN");
        assertEquals("ROLE_ADMIN", authority.getAuthority());
    }
}
