package com.sparta.second.dto;

import lombok.Getter;

import java.net.URL;


@Getter
public class ReviewRequestDto {
    private String Content;
    private URL ImageUrl;
    private Long ShopId;
}
