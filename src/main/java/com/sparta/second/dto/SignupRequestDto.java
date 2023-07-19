package com.sparta.second.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequestDto {

    @NotBlank
    @Pattern(regexp = "^[a-z0-9]{4,10}$")
    private String userName;

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]{8,15}$")
    private String userPassword;

    @NotBlank
//    @Email
    private String userEmail;

    @NotBlank
    private String userNick;
}



