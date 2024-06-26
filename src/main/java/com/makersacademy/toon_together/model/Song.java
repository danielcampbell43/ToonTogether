package com.makersacademy.toon_together.model;

import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.minidev.json.JSONObject;
import org.hibernate.annotations.CreationTimestamp;

import java.util.List;
import java.util.Set;
@Data
@Entity
@Table(name = "songs")

@Data

@NoArgsConstructor
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

    public String getTrackName() {
        return trackName;
    }

    public String getTrackArtist() {
        return trackArtist;
    }

    public String getAlbum() {
        return album;
    }

    public String getReleaseYear() {
        return releaseYear;
    }

    public String getImage() {
        return image;
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


