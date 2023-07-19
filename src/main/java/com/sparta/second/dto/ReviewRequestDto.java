package com.sparta.second.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.net.URL;


@Getter
//@NoArgsConstructor
public class ReviewRequestDto {
    private String content;
    private URL imageUrl;
    private Long shopId;
}
