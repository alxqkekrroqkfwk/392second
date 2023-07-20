package com.sparta.second.dto;

import com.sparta.second.entity.Review;
import com.sparta.second.entity.User;
import lombok.Getter;

import java.net.URL;
import java.time.LocalDateTime;

@Getter
public class ReviewResponseDto {
    private String content;
    private URL imageUrl;
    private Integer likeCount;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;
    private Integer likeCount;

    public ReviewResponseDto(Review review) {
        this.content = review.getContent();
        this.imageUrl = review.getImageUrl();
        this.likeCount = review.getReviewLikes().size();
        this.createdAt = review.getCreatedAt();
        this.modifiedAt = review.getModifiedAt();
        this.likeCount = review.getReviewLikes().size();
    }
}
