package com.sparta.second.dto;

import java.util.List;

public class ReviewListResponseDto {
    List<ReviewResponseDto> reviewList;

    public ReviewListResponseDto(List<ReviewResponseDto> reviewList) {
        this.reviewList = reviewList;
    }
}
