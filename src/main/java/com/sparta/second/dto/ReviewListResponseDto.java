package com.sparta.second.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class ReviewListResponseDto {
    private List<ReviewResponseDto> reviewList;

    public ReviewListResponseDto(List<ReviewResponseDto> reviewList) {
        this.reviewList = reviewList;
    }
}
