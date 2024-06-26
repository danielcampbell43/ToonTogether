package com.makersacademy.toon_together.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "AUTHORITIES")
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String authority;

    public Authority() {}

    public Authority(String username, String authority) {
        this.username = username;
        this.authority = authority;
    }
}
