package com.sparta.second.dto;

import com.sparta.second.entity.Order;
import com.sparta.second.entity.Review;
import com.sparta.second.entity.User;
import lombok.Getter;

import java.net.URL;
import java.time.LocalDateTime;

@Getter
public class ReviewResponseDto {
    private String content;
    private URL imageUrl;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public ReviewResponseDto(Review review) {
        this.content = review.getContent();
        this.imageUrl = review.getImageUrl();
        this.createdAt = review.getCreatedAt();
        this.modifiedAt = review.getModifiedAt();
    }
}
