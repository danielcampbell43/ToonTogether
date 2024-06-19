package com.makersacademy.toon_together.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;

@Data
@Entity
@Table(name = "playlist_songs")
public class PlaylistSong {
    @Id
    private int id;

    @ManyToOne
    @JoinColumn(name = "playlist_id")
    Playlist playlist;

    @ManyToOne
    @JoinColumn(name = "song_id")
    Song song;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private java.sql.Timestamp createdAt;

    public PlaylistSong(Playlist playlist, Song song, java.sql.Timestamp createdAt) {
        this.playlist = playlist;
        this.song = song;
        this.createdAt = createdAt;
    }
}
