package com.makersacademy.toon_together.model;

import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.List;
@Data
@Entity()
@Table(name = "playlists")
@NoArgsConstructor
public class Playlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "owner", nullable = false)
    private User owner;

    private String name;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.sql.Timestamp createdAt;

//    @OneToMany(mappedBy = "playlist", cascade = CascadeType.ALL)
//    private List<Collaborator> collaborators;

    @OneToMany(mappedBy = "playlist")
    private List<PlaylistSong> playlistSongs;

    @OneToMany(mappedBy = "playlist", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Favourite> favouritePlaylist;

    public Playlist(User owner, String name, java.sql.Timestamp createdAt) {
        this.owner = owner;
        this.name = name;
        this.createdAt = createdAt;
//        this.collaborators = new ArrayList<>();
    }

    public boolean containsUser(String username) {
        return favouritePlaylist.stream().anyMatch(favourite -> favourite.getUser().getUsername().equals(username));
    }
}
