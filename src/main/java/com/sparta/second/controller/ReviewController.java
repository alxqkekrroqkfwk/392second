package com.sparta.second.controller;

import com.sparta.second.dto.MsgResponseDto;
import com.sparta.second.dto.ReviewListResponseDto;
import com.sparta.second.dto.ReviewRequestDto;
import com.sparta.second.dto.ReviewResponseDto;
import com.sparta.second.security.UserDetailsImpl;
import com.sparta.second.service.ReviewService;
import com.sun.jdi.request.DuplicateRequestException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
@RequestMapping("/api")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/{orderId}/review")
    public ResponseEntity<MsgResponseDto> createReview(@PathVariable Long orderId,@AuthenticationPrincipal UserDetailsImpl userDetails ,@RequestBody ReviewRequestDto reviewRequestDto) {
        reviewService.createReview(orderId,userDetails.getUser(),reviewRequestDto);
        return ResponseEntity.ok().body(new MsgResponseDto("리뷰 생성 완료 !", HttpStatus.OK.value()));
    }

    @GetMapping("/review/{review_Id}")
    public ResponseEntity<ReviewResponseDto> getreview(@PathVariable Long reviewId) {
        ReviewResponseDto reviewResponseDto=reviewService.getReview(reviewId);
        return ResponseEntity.ok().body(reviewResponseDto);
    }

    @GetMapping("/reviews")
    public ResponseEntity<ReviewListResponseDto> getreviews() {
        ReviewListResponseDto reviewListResponseDto=reviewService.getReviews();
        return ResponseEntity.ok().body(reviewListResponseDto);
    }

    @Transactional
    @PutMapping("/review/{reviewId}")
    public ResponseEntity<MsgResponseDto> update(@AuthenticationPrincipal UserDetailsImpl userDetails,@PathVariable Long reviewId,@RequestBody ReviewRequestDto reviewRequestDto) {
        reviewService.update(userDetails.getUser(), reviewId,reviewRequestDto);
        return ResponseEntity.ok().body(new MsgResponseDto("리뷰 수정 완료 !", HttpStatus.OK.value()));
    }

    @DeleteMapping("/review/{reviewId}")
    public ResponseEntity<MsgResponseDto> delete(@AuthenticationPrincipal UserDetailsImpl userDetails,@PathVariable Long reviewId) {
        reviewService.delete(userDetails.getUser(),reviewId);
        return ResponseEntity.ok().body(new MsgResponseDto("리뷰 삭제 완료 !",HttpStatus.OK.value()));
    }

    @PostMapping("/review/{reviewId}/like")
    public ResponseEntity<MsgResponseDto> createLike(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long reviewId) {
        try {
            reviewService.createLike(reviewId, userDetails.getUser());
        } catch (DuplicateRequestException e) {
            return ResponseEntity.badRequest().body(new MsgResponseDto(e.getMessage(), HttpStatus.BAD_REQUEST.value()));
        }

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new MsgResponseDto("댓글 좋아요 성공", HttpStatus.ACCEPTED.value()));
    }


    @DeleteMapping("/review/{reviewId}/like")
    public ResponseEntity<MsgResponseDto> deleteLike(@PathVariable Long reviewId, @AuthenticationPrincipal UserDetailsImpl userDetails){
        try {
            reviewService.deleteLike(reviewId,userDetails.getUser());
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(new MsgResponseDto(e.getMessage(), HttpStatus.BAD_REQUEST.value()));
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new MsgResponseDto("좋아요를 취소 하였습니다",HttpStatus.ACCEPTED.value()));
    }
}
