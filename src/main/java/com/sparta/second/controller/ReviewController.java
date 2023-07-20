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
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/api/review")
    public ResponseEntity<MsgResponseDto> createReview(@AuthenticationPrincipal UserDetailsImpl userDetails,@RequestBody ReviewRequestDto reviewRequestDto) {
        reviewService.createReview(userDetails,reviewRequestDto);
        return ResponseEntity.ok().body(new MsgResponseDto("리뷰 생성 성공 !", HttpStatus.OK.value()));
    }

    @GetMapping("/api/review/{review_Id}")
    public ResponseEntity<ReviewResponseDto> getreview(@PathVariable Long review_Id) {
        ReviewResponseDto reviewResponseDto=reviewService.getReview(review_Id);
        return ResponseEntity.ok().body(reviewResponseDto);
    }

    @GetMapping("/api/reviews")
    public ResponseEntity<ReviewListResponseDto> getreviews() {
        ReviewListResponseDto reviewListResponseDto=reviewService.getReviews();
        return ResponseEntity.ok().body(reviewListResponseDto);
    }
    @Transactional
    @PutMapping("/api/review/{review_Id}")
    public ResponseEntity<MsgResponseDto> update(@AuthenticationPrincipal UserDetailsImpl userDetails,@PathVariable Long review_Id,@RequestBody ReviewRequestDto reviewRequestDto) {
        reviewService.update(userDetails.getUser(), review_Id,reviewRequestDto);
        return ResponseEntity.ok().body(new MsgResponseDto("리뷰 수정 완료", HttpStatus.OK.value()));
    }

    @DeleteMapping("api/review/{review_Id}")
    public ResponseEntity<MsgResponseDto> delete(@AuthenticationPrincipal UserDetailsImpl userDetails,@PathVariable Long review_Id) {
        reviewService.delete(userDetails.getUser(),review_Id);
        return ResponseEntity.ok().body(new MsgResponseDto("리뷰 삭제 성공",HttpStatus.OK.value()));
    }

    @PostMapping("/comments/{id}/like")
    public ResponseEntity<MsgResponseDto> createLike(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long id) {
        try {
            reviewService.createLike(id, userDetails.getUser());
        } catch (DuplicateRequestException e) {
            return ResponseEntity.badRequest().body(new MsgResponseDto(e.getMessage(), HttpStatus.BAD_REQUEST.value()));
        }

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new MsgResponseDto("댓글 좋아요 성공", HttpStatus.ACCEPTED.value()));
    }

    // 리뷰에 좋아요 삭제
    @DeleteMapping("api/review/{reviewId}/like")
    public ResponseEntity<MsgResponseDto> deleteLike(@PathVariable Long reviewId, @AuthenticationPrincipal UserDetailsImpl userDetails){
        try {
            reviewService.deleteLike(reviewId,userDetails.getUser());
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(new MsgResponseDto(e.getMessage(), HttpStatus.BAD_REQUEST.value()));
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new MsgResponseDto("좋아요를 취소 하였습니다",HttpStatus.ACCEPTED.value()));
    }
}
