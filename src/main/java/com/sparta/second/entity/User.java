package com.sparta.second.entity;

import com.sparta.second.dto.ProfileUpdateDto;
import com.sparta.second.dto.UserRequestDto;
import com.sparta.second.dto.UserResponseDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 유저 이름
    @Column(nullable = false, unique = true)
    private String userName;

    // 유저 비밀번호
    @Column(nullable = false)
    private String userPassword;

    // 유저 닉네임
    @Column(nullable = false)
    private String userNick;

    // 유저 이메일
    @Column(nullable = false)
    private String userEmail;

    // 유저 자기소개
    @Column
    private String myContent;

    public User(UserRequestDto userRequestDto) {
        this.userName = userRequestDto.getUserName();
        this.userPassword = userRequestDto.getUserPassword();
        this.userNick = userRequestDto.getUserNick();
        this.userEmail = userRequestDto.getUserEmail();
    }

    public void updateProfile(ProfileUpdateDto profileUpdateDto) {
        this.userEmail = profileUpdateDto.getUserEmail();
        this.userPassword = profileUpdateDto.getUserPassword();
        this.userNick = profileUpdateDto.getUserNick();
        this.myContent = profileUpdateDto.getMyContent();
    }
}
