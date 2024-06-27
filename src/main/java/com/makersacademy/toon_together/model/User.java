package com.makersacademy.toon_together.model;

import javax.persistence.*;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Boolean.TRUE;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String password;
    private boolean enabled;

    @Column(name = "profile_picture")
    private String profilePicture;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private java.sql.Timestamp createdAt;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Favourite> userFavourites;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Collaborator> collaboratorPlaylists;

    @javax.persistence.Transient private String friend_status;

    public User() {
        this.enabled = TRUE;
    }

    public User(String username, String password, String profilePicture, java.sql.Timestamp createdAt) {
        this.username = username;
        this.password = password;
        this.enabled = TRUE;
        this.profilePicture = profilePicture;
        this.createdAt = createdAt;
    }

    public User(String username, String password, boolean enabled, String profilePicture, java.sql.Timestamp createdAt) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.profilePicture = profilePicture;
        this.createdAt = createdAt;
    }

    public List<Integer> getFavouritePlaylists() {
        return userFavourites.stream()
                .map(favourite -> favourite.getPlaylist().getId())
                .collect(Collectors.toList());
    }

    public List<Integer> getCollaboratorPlaylists() {
        return collaboratorPlaylists.stream()
                .map(collaborator -> collaborator.getPlaylist().getId())
                .collect(Collectors.toList());
    }
}
