package com.sparta.second.requestDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class signupRequestDto {

    @NotBlank
    private String userName;

    @NotBlank
    private String userPassword;

    @NotBlank
    @Email
    private String userEmail;







}



