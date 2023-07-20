package com.sparta.second.repository;

import com.sparta.second.entity.Review;
import com.sparta.second.entity.ReviewLike;
import com.sparta.second.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Long> {
    List<Review> findAllByOrderByModifiedAtDesc();
}
