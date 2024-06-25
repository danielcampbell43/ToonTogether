package com.makersacademy.toon_together.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "collaborators")
public class Collaborator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "playlist_id", nullable = false)
    private Playlist playlist;

    @Column(name = "deleter", nullable = false)
    private boolean deleter;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    public Collaborator(User user, Playlist playlist) {
        this.user = user;
        this.playlist = playlist;
        this.deleter = false;
    }

    public Collaborator(User user, Playlist playlist, Boolean deleter) {
        this.user = user;
        this.playlist = playlist;
        this.deleter = deleter;
    }
}

