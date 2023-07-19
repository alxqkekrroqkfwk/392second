package com.sparta.second.dto;

import com.sparta.second.entity.Review;
import com.sparta.second.entity.User;

import java.net.URL;
import java.time.LocalDateTime;

public class ReviewResponseDto {
    private String content;
    private URL ImageUrl;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public ReviewResponseDto(Review review) {
        this.content = review.getContent();
        this.ImageUrl = review.getImageUrl();
        this.createdAt = review.getCreatedAt();
        this.modifiedAt = review.getModifiedAt();
    }
}
