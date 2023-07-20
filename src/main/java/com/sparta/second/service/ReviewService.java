package com.sparta.second.service;

import com.sparta.second.dto.ReviewListResponseDto;
import com.sparta.second.dto.ReviewRequestDto;
import com.sparta.second.dto.ReviewResponseDto;
import com.sparta.second.entity.Review;
import com.sparta.second.entity.ReviewLike;
import com.sparta.second.entity.Shop;
import com.sparta.second.entity.User;
import com.sparta.second.repository.ReviewLikeRepository;
import com.sparta.second.repository.ReviewRepository;
import com.sparta.second.repository.ShopRepository;
import com.sparta.second.security.UserDetailsImpl;
import com.sun.jdi.request.DuplicateRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.RejectedExecutionException;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ShopRepository shopRepository;
    private final ReviewLikeRepository reviewLikeRepository;

    public void createReview(UserDetailsImpl userDetails, ReviewRequestDto reviewRequestDto) {
        Shop shop = shopRepository.findById(reviewRequestDto.getShopId()).orElseThrow(() -> new RuntimeException());

        Review review = new Review(userDetails.getUser(),reviewRequestDto,shop);

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

        if (!review.getUser().equals(user)) {
            throw new RejectedExecutionException();
        }
        review.update(reviewRequestDto);

        reviewRepository.save(review);
    }

    public void delete(User user,Long reviewId) {
        // 해당 포스트가 DB에 존재하는지 확인
        Review review = findReview(reviewId);
        //리뷰 작성자와 요청자(user)가 같은지 학인
        if (!review.getUser().equals(user)) {
            throw new RejectedExecutionException();
        }
        // post 삭제
        reviewRepository.delete(review);
    }
    private Review findReview(Long reviewId) {
        return reviewRepository.findById(reviewId).orElseThrow(() ->
                new IllegalArgumentException("선택한 게시물이 없습니다."));
    }

    @Transactional
    public void createLike(Long id, User user) {
        Review review = findReview(id);

        if (reviewLikeRepository.existsByUserAndReview(user, review)) {
            throw new DuplicateRequestException("이미 좋아요 한 댓글 입니다.");
        } else {
            ReviewLike reviewLike = new ReviewLike(user, review);
            reviewLikeRepository.save(reviewLike);
        }
    }


    // 리뷰에 좋아요 삭제
    @Transactional
    public void deleteLike(Long reviewId, User user) {
        Review review = findReview(reviewId);
        Optional<ReviewLike> reviewLike = reviewLikeRepository.findByUserAndReview(user, review);
        if(reviewLike.isPresent()){
            reviewLikeRepository.delete(reviewLike.get());
        } else{
            throw new IllegalArgumentException("좋아요한 댓글이 아닙니다.");
        }
    }
}
