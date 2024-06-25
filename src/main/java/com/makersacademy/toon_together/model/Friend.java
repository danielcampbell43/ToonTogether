package com.makersacademy.toon_together.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;

import java.sql.Timestamp;

import static java.lang.Boolean.FALSE;

@Data
@Entity
@Table(name = "Friends")
public class Friend {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private User sender;
    @ManyToOne
    @JoinColumn(name = "recipient_id")
    private User recipient;
    private boolean accepted;
    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private Timestamp createdAt;

    public Friend() {
        this.accepted = FALSE;
    }

    public Friend(User sender, User recipient) {
        this.sender = sender;
        this.recipient = recipient;
        this.accepted = FALSE;
    }


}
