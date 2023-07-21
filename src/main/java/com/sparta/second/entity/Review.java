package com.sparta.second.entity;

import com.sparta.second.dto.ReviewRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.aspectj.weaver.ast.Or;

import java.net.URL;
import java.sql.Timestamp;
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
    @OneToOne
    @JoinColumn(name = "menu_id")
    private Order order;


    public Review(User user , ReviewRequestDto reviewRequestDto) {
        this.user = user;

        this.content = reviewRequestDto.getContent();
        this.ImageUrl = reviewRequestDto.getImageUrl();
    }

    public void update(ReviewRequestDto reviewRequestDto) {
        this.content = reviewRequestDto.getContent();
        this.ImageUrl = reviewRequestDto.getImageUrl();
    }
}
