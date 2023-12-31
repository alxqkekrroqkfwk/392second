package com.sparta.second.entity;


import com.sparta.second.dto.ProfileUpdateDto;
import com.sparta.second.dto.UserRequestDto;
import com.sparta.second.dto.UserRoleEnum;
import jakarta.persistence.*;
import jakarta.persistence.criteria.Order;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.net.URL;
import java.util.List;

@Getter
@Entity
@Setter
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false,unique = true)
    private String userName;

    @Column(nullable = false)
    private String userPassword;

    @Column(nullable = false)
    private String userNick;

    @Column(nullable = false)
//    @Email
    private String userEmail;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;

    @Column
    private String myContent;

    @Column
    private URL myImage;

    @Column
    private int money;

//    @OneToMany(mappedBy = "user", orphanRemoval = true)
//    private List<Shop> shops;
//
    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private List<ReviewLike> reviewLikes;

//    @OneToMany(mappedBy = "user", orphanRemoval = true)
//    private List<Order> orders;


    public User(String username, String password, String email, String Nick, UserRoleEnum role) {
    this.userName = username;
    this.userPassword = password;
    this.userNick = Nick;
    this.userEmail = email;
    this.role = role;
}

    public void updateProfile(ProfileUpdateDto profileUpdateDto) {
        this.userEmail = profileUpdateDto.getUserEmail();
        this.userPassword = profileUpdateDto.getChangePassword();
        this.userNick = profileUpdateDto.getUserNick();
        this.myContent = profileUpdateDto.getMyContent();
    }
}