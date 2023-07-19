package com.sparta.second.entity;

import com.sparta.second.dto.ReviewRequestDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.net.URL;
import java.sql.Timestamp;


@Getter
@Entity
@NoArgsConstructor
public class Review extends TimeStamped {

    @Id
    @Column
    private Long reviewId;
    @Column
    @ManyToOne
    private User user;
//    private Shop shop;
    @Column
    private String content;
    @Column
    private URL ImageUrl;

    public Review(User user , ReviewRequestDto reviewRequestDto) {
        this.user = user;
//        this.shop = shop;
        this.content = reviewRequestDto.getContent();
        this.ImageUrl = reviewRequestDto.getImageUrl();
    }

    public void update(ReviewRequestDto reviewRequestDto) {
        this.content = reviewRequestDto.getContent();
        this.ImageUrl = reviewRequestDto.getImageUrl();
    }
}
