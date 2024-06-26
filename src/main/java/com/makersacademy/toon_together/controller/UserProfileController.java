package com.makersacademy.toon_together.controller;

import com.makersacademy.toon_together.model.Friend;
import com.makersacademy.toon_together.model.Playlist;
import com.makersacademy.toon_together.model.User;
import com.makersacademy.toon_together.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.*;
import java.util.stream.Collectors;

@Controller
public class UserProfileController {
    @Autowired
    PlaylistRepository playlistRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    FriendRepository friendRepository;
    @Autowired
    CollaboratorRepository collaboratorRepository;

    public ArrayList<User> getFriends(User sessionUser) {
        List<Friend> received = friendRepository.findAllByRecipient(sessionUser);
        List<Friend> sent = friendRepository.findAllBySender(sessionUser);

        ArrayList<User> friends = new ArrayList<>();
        for (Friend connection: received) {
            friends.add(connection.getSender());
        }

        for (Friend connection: sent) {
            friends.add(connection.getRecipient());
        }

        return friends;
    }

    public void setFriendStatus(User sessionUser, User otherUser) {
            otherUser.setFriend_status(FriendsController.GetFriendStatus(sessionUser, otherUser, friendRepository));
    }


    @RequestMapping(value = "/myprofile")
    public String myProfile(Model model, Authentication authentication) {
        if (authentication != null) {
            String username = authentication.getName();
            User user = userRepository.findByUsername(username);
            List<Playlist> playlists = playlistRepository.findByOwner(userRepository.findByUsername(username));
            for (int i : user.getCollaboratorPlaylists()) {
                playlists.add(playlistRepository.findById(i));
            }
            model.addAttribute("playlists", playlists);
            model.addAttribute("user", user);
            model.addAttribute("favourites", user.getFavouritePlaylists().stream()
                    .map(i -> playlistRepository.findById(i).orElse(null))
                    .collect(Collectors.toList()));
        } else {
            model.addAttribute("playlists", null);
        }
        return "my-profile";
    }

    @DeleteMapping("/myprofile")
    public RedirectView delete(@RequestParam int id, Authentication auth) {
        User user = userRepository.findByUsername(auth.getName());
        Playlist playlist = playlistRepository.findById(id);
        if (user.getId() == playlist.getOwner().getId()) {
            playlistRepository.deleteById(id);
        }
        return new RedirectView("/myprofile");
    }

    @PostMapping("/myprofile/updateProfilePicture")
    public RedirectView updateProfilePicture(@RequestParam("profilePicture") String profilePictureUrl, Authentication auth) {
        User user = userRepository.findByUsername(auth.getName());
        if (user != null) {
            user.setProfilePicture(profilePictureUrl);
            userRepository.save(user);
        }
        return new RedirectView("/myprofile");
    }

    @GetMapping("/users/{username}")
    public String viewUserProfile(@PathVariable("username") String username, Model model, Authentication auth) {
        User profileUser = userRepository.findByUsername(username);
        User sessionUser = userRepository.findByUsername(auth.getName());
        if (profileUser == null) {
            return "redirect:/"; // or handle as a 404 not found
        }
        setFriendStatus(sessionUser, profileUser);
        List<Playlist> playlists = playlistRepository.findByOwner(profileUser);
        model.addAttribute("profileUser", profileUser);
        model.addAttribute("playlists", playlists);
        return "user-profile";
    }

    @GetMapping("/users/friends")
    public String seeUserFriends(Model model, Authentication auth) {
        User sessionUser = userRepository.findByUsername(auth.getName());

        ArrayList<User> friends = getFriends(sessionUser);
        for (User friend : friends) {
            setFriendStatus(sessionUser, friend);
        }

        model.addAttribute("sessionUser", sessionUser);
        model.addAttribute("friends", friends);
        return "user-friend-index";
    }
}
