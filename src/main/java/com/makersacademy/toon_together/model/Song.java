package com.makersacademy.toon_together.model;

import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.minidev.json.JSONObject;
import org.hibernate.annotations.CreationTimestamp;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "songs")
public class Song {
    @Id
    private String id;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private java.sql.Timestamp createdAt;

    @Column(name = "track_name")
    private String trackName;

    @Column(name = "track_artist")
    private String trackArtist;

    @Column(name = "album")
    private String album;

    @Column(name = "release_year")
    private String releaseYear;

    @Column(name = "image")
    private String image;

//    @OneToMany(mappedBy = "song")
//    private Set<PlaylistSong> playlistSongs;



    public Song() {

    }

    public Song(String id, String trackName, String trackArtist, String album, String releaseYear, String image) {
        this.id = id;
        this.trackName = trackName;
        this.trackArtist = trackArtist;
        this.album = album;
        this.releaseYear = releaseYear;
        this.image = image;
        this.createdAt = new java.sql.Timestamp(System.currentTimeMillis());
    }
}


