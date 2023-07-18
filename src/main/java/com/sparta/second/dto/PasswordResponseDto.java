package com.sparta.second.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class PasswordResponseDto {
    boolean isCorrect;

    public PasswordResponseDto(boolean isCorrect) {
        this.isCorrect = isCorrect;
    }
}
