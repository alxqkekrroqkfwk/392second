package com.sparta.second.service;

import com.sparta.second.dto.ReviewListResponseDto;
import com.sparta.second.dto.ReviewRequestDto;
import com.sparta.second.dto.ReviewResponseDto;
import com.sparta.second.entity.Review;
import com.sparta.second.entity.User;
import com.sparta.second.repository.ReviewRepository;
import com.sparta.second.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.RejectedExecutionException;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
//    private final ShopRepository shopRepository;
    public void createReview(UserDetailsImpl userDetails, ReviewRequestDto reviewRequestDto) {
//        Shop shop = shopRepository.findById(reviewRequestDto.getShopId()).orElseThrow(() -> new RuntimeException());

        Review review = new Review(userDetails.getUser(),reviewRequestDto);

        reviewRepository.save(review);
    }

    public ReviewResponseDto getReview(Long reviewId) {
        Review review = findReview(reviewId);

        ReviewResponseDto reviewResponseDto = new ReviewResponseDto(review);

        return reviewResponseDto;
    }

    public ReviewListResponseDto getReviews() {
        List<ReviewResponseDto> reviewList = reviewRepository.findAllByOrderByModifiedAtDesc()
                .stream().map(ReviewResponseDto::new).collect(Collectors.toList());

        return new ReviewListResponseDto(reviewList);
    }

    public void update(User user, Long reviewId, ReviewRequestDto reviewRequestDto) {
        Review review = findReview(reviewId);

//        if (!review.getUser().equals(user)) {
//            throw new RejectedExecutionException();
//        }
        review.update(reviewRequestDto);

        reviewRepository.save(review);
    }

    public void delete(User user,Long reviewId) {
        // 해당 포스트가 DB에 존재하는지 확인
        Review review = findReview(reviewId);
        //리뷰 작성자와 요청자(user)가 같은지 학인
//        if (!review.getUser().equals(user)) {
//            throw new RejectedExecutionException();
//        }
        // post 삭제
        reviewRepository.delete(review);
    }
    private Review findReview(Long reviewId) {
        return reviewRepository.findById(reviewId).orElseThrow(() ->
                new IllegalArgumentException("선택한 게시물이 없습니다."));
    }
}
