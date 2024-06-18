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
}
