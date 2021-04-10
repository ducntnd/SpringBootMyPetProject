package com.example.security.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "User")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String password;
    private boolean active;

    @Column
    private String roles;
}
