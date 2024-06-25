package com.makersacademy.toon_together.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import com.makersacademy.toon_together.controller.SpotifyController;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "playlist_songs")
public class PlaylistSong {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "playlist_id")
    private int playlistId;

    @Column(name = "song_id")
    private String songId;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private Timestamp createdAt;

    // Constructors, getters, and setters
    public PlaylistSong() {
        // Default constructor needed by JPA
    }

    public PlaylistSong(int playlistId, String songId) {
        this.playlistId = playlistId;
        this.songId = songId;
    }
}
