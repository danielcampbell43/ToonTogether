package com.makersacademy.toon_together.controller;

import com.makersacademy.toon_together.model.Friend;
import com.makersacademy.toon_together.model.User;
import com.makersacademy.toon_together.repository.AuthoritiesRepository;
import com.makersacademy.toon_together.repository.FriendRepository;
import com.makersacademy.toon_together.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Optional;

@Controller
public class FriendsController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    AuthoritiesRepository authoritiesRepository;
    @Autowired
    FriendRepository friendRepository;

    public static String GetFriendStatus(User sender, User recipient, FriendRepository friendRepository){

        if (sender == null || recipient == null || sender.getId() == recipient.getId()) {
            return "N/A";
        }

        Optional<Friend> existingConnection = friendRepository.findBySenderAndRecipient(sender, recipient);
        if (existingConnection.isPresent()){
            if (existingConnection.get().isAccepted()) return "Friend";
            return "Sent";
        }

        existingConnection = friendRepository.findBySenderAndRecipient(recipient, sender);
        if (existingConnection.isPresent()){
            if (existingConnection.get().isAccepted()) {
                return "Friend";
            }
            return "Received";
        }
        return "None";
    }

    @PostMapping("/friends/add")
    public RedirectView addFriend(@RequestParam String recipient_username, Authentication auth, @RequestParam String returnURL) {
        User sender = userRepository.findByUsername(auth.getName());
        User recipient = userRepository.findByUsername(recipient_username);

        Friend newConnection = new Friend(sender, recipient);
        friendRepository.save(newConnection);
        return new RedirectView(returnURL);
    }

    @PostMapping("/friends/confirm")
    public RedirectView confirmFriend(@RequestParam String recipient_username, Authentication auth, @RequestParam String returnURL) {
        User sender = userRepository.findByUsername(auth.getName());
        User recipient = userRepository.findByUsername(recipient_username);

        Optional<Friend> existingConnection = friendRepository.findBySenderAndRecipient(recipient, sender);
        if (!existingConnection.isPresent()) return new RedirectView(returnURL);
        Friend connection = existingConnection.get();
        connection.setAccepted(true);
        friendRepository.save(connection);
        return new RedirectView(returnURL);
    }

    @PostMapping("/friends/delete")
    public RedirectView deleteFriend(@RequestParam String recipient_username, Authentication auth, @RequestParam String returnURL) {
        User sender = userRepository.findByUsername(auth.getName());
        User recipient = userRepository.findByUsername(recipient_username);

        Optional<Friend> sentConnection = friendRepository.findBySenderAndRecipient(sender, recipient);
        Optional<Friend> receivedConnection = friendRepository.findBySenderAndRecipient(recipient, sender);

        sentConnection.ifPresent(friend -> friendRepository.delete(friend));
        receivedConnection.ifPresent(friend -> friendRepository.delete(friend));

        return new RedirectView(returnURL);
    }
}
