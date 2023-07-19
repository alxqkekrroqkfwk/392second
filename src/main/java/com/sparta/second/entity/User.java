package com.sparta.second.entity;


import com.sparta.second.dto.ProfileUpdateDto;
import com.sparta.second.dto.UserRequestDto;
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
    private Long user_id;

    @Column(nullable = false,unique = true)
    private String userName;

    @Column(nullable = false)
    private String userPassword;

    @Column(nullable = false)
    private String userNick;

    @Column(nullable = false)
//    @Email
    private String userEmail;

    @Column
    private String myContent;

    @Column
    private URL myImage;

    public User(String username, String password, String email, String Nick) {
    this.userName = username;
    this.userPassword = password;
    this.userNick = email;
    this.userEmail = Nick;
}

    public void updateProfile(ProfileUpdateDto profileUpdateDto) {
        this.userEmail = profileUpdateDto.getUserEmail();
        this.userNick = profileUpdateDto.getUserNick();
        this.myContent = profileUpdateDto.getMyContent();
    }
}