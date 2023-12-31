package com.sparta.second.entity;

import com.sparta.second.dto.ReviewRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.aspectj.weaver.ast.Or;

import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
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
    @Column
    private int count;
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;
    @OneToOne
    @JoinColumn(name = "orderId")
    private Order order;
    @OneToMany(mappedBy = "review", orphanRemoval = true)
    private List<ReviewLike> reviewLikes = new ArrayList<>();


    public Review(User user ,Order order , ReviewRequestDto reviewRequestDto) {
        this.user = user;
        this.order = order;
        this.content = reviewRequestDto.getContent();
        this.ImageUrl = reviewRequestDto.getImageUrl();
    }

    public void update(ReviewRequestDto reviewRequestDto) {
        this.content = reviewRequestDto.getContent();
        this.ImageUrl = reviewRequestDto.getImageUrl();
    }
}
