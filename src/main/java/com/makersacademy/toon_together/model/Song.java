package com.makersacademy.toon_together.model;

import javax.persistence.*;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.util.List;

@Data
@Entity
@Table(name = "songs")
public class Song {
    @Id
    private String id;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private java.sql.Timestamp createdAt;

    @OneToMany(mappedBy = "song")
    List<PlaylistSong> playlistSongs;

    public Song(java.sql.Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
