package com.makersacademy.toon_together.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.sql.Timestamp;

public class FriendTest {

    private User sender;
    private User recipient;
    private Friend friend;

    @Before
    public void setUp() {
        sender = new User();
        sender.setId(1);
        recipient = new User();
        recipient.setId(2);
        friend = new Friend(sender, recipient);
        friend.setCreatedAt(new Timestamp(10000));
    }

    @Test
    public void testDefaultConstructor() {
        Friend friend = new Friend();
        assertNotNull(friend);
        assertFalse(friend.isAccepted());
    }

    @Test
    public void testParameterizedConstructor() {
        Friend friend = new Friend(sender, recipient);
        assertEquals(sender, friend.getSender());
        assertEquals(recipient, friend.getRecipient());
        assertFalse(friend.isAccepted());
    }

    @Test
    public void testGetSender() {
        User newSender = new User();
        newSender.setId(3);
        friend.setSender(newSender);
        assertEquals(newSender, friend.getSender());
    }

    @Test
    public void testGetRecipient() {
        User newRecipient = new User();
        newRecipient.setId(4);
    }
}