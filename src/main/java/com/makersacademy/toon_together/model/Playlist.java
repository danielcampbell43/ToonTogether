package com.makersacademy.toon_together.model;

import javax.persistence.*;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.util.List;

@Data
@Entity
@Table(name = "playlists")
public class Playlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "owner", nullable = false)
    private User user;

    private String name;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private java.sql.Timestamp createdAt;

    @OneToMany(mappedBy = "playlist")
    List<PlaylistSong> playlistSongs;

    public Playlist(User user, String name, java.sql.Timestamp createdAt) {
        this.user = user;
        this.name = name;
        this.createdAt = createdAt;
    }
}
