package com.sparta.second.dto;

import com.sparta.second.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class UserResponseDto {
    private String userName;
//    private String userPassword;
    private String userNick;
    private String userEmail;
    private String myContent;

    public UserResponseDto(User user) {
        this.userName = user.getUserName();
//        this.userPassword = user.getUserPassword();
        this.userNick = user.getUserNick();
        this.userEmail = user.getUserEmail();
        this.myContent = user.getMyContent();
    }
}
