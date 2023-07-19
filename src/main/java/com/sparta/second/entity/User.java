package com.sparta.second.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.net.URL;

@Getter
@Entity
@Setter
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,unique = true)
    private String userName;

    @Column(nullable = false)
    private String userPassword;

    @Column(nullable = false)
    private String getUserNick;

    @Column(nullable = false)
    @Email
    private String userEmail;

    @Column
    private String myContent;

    @Column
    private URL myImage;

//    @Column
//    @OneToMany
//    private Review review;
}