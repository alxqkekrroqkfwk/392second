package com.sparta.second.entity;

import com.sparta.second.dto.ReviewRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;


@Getter
@Entity
@NoArgsConstructor
public class Review extends TimeStamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;
    @Column
    private String content;
    @Column
    private URL ImageUrl;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "shop_id")
    private Shop shop;

    @OneToMany(mappedBy = "Review", cascade = CascadeType.REMOVE)
    private List<ReviewLike> reviewLikes = new ArrayList<>();

    public Review(User user , ReviewRequestDto reviewRequestDto,Shop shop) {
        this.user = user;
        this.shop = shop;
        this.content = reviewRequestDto.getContent();
        this.ImageUrl = reviewRequestDto.getImageUrl();
    }

    public void update(ReviewRequestDto reviewRequestDto) {
        this.content = reviewRequestDto.getContent();
        this.ImageUrl = reviewRequestDto.getImageUrl();
    }
}
