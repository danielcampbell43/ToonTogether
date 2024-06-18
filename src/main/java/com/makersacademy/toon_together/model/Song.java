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
    private int id;

    @OneToMany(mappedBy = "song")
    List<PlaylistSong> playlistSongs;
}
