package com.sparta.second.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequestDto {

    @NotBlank
    private String userName;

    @NotBlank
    private String userPassword;

    @NotBlank
//    @Email
    private String userEmail;

    @NotBlank
    private String userNick;
}



