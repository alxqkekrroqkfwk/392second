package com.sparta.second.repository;

import com.sparta.second.entity.Review;
import com.sparta.second.entity.ReviewLike;
import com.sparta.second.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReviewLikeRepository extends JpaRepository<ReviewLike, Long> {
    Optional<ReviewLike> findByUserAndReview(User user, Review Review);

}
