package com.sparta.second.controller;

import com.sparta.second.dto.MsgResponseDto;
import com.sparta.second.dto.ShopListResponseDto;
import com.sparta.second.dto.ShopRequestDto;
import com.sparta.second.dto.ShopResponseDto;
import com.sparta.second.security.UserDetailsImpl;
import com.sparta.second.service.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.RejectedExecutionException;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ShopController {

    private final ShopService shopService;

    // 가게 조회
    @GetMapping("/shops")
    public ResponseEntity<ShopListResponseDto> getShops() {
        ShopListResponseDto shopListResponseDto = shopService.getShops();
        return ResponseEntity.ok().body(shopListResponseDto);
    }

    @GetMapping("/shop/{shopId}")
    public ResponseEntity<ShopResponseDto> getShop(@PathVariable Long shopId) {
        ShopResponseDto shopResponseDto = new ShopResponseDto(shopService.findShop(shopId));
        return ResponseEntity.ok().body(shopResponseDto);
    }

    @PostMapping("/shop")
    public ResponseEntity<MsgResponseDto> createShop(@RequestBody ShopRequestDto requestDto) {
        shopService.createShop(requestDto);
        return ResponseEntity.ok().body(new MsgResponseDto("가게 생성 성공", HttpStatus.OK.value()));
    }

    @DeleteMapping("/shop/{id}")
    public ResponseEntity<com.sparta.second.dto.MsgResponseDto> deleteShop(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long id) {
        try {
            shopService.deleteShop(id, userDetails.getUser());
            return ResponseEntity.ok().body(new com.sparta.second.dto.MsgResponseDto("가게 삭제 성공", HttpStatus.OK.value()));
        } catch (RejectedExecutionException e) {
            return ResponseEntity.badRequest().body(new com.sparta.second.dto.MsgResponseDto("가게 삭제 실패", HttpStatus.BAD_REQUEST.value()));
        }
    }

    @PutMapping("/shop/{id}")
    public ResponseEntity<com.sparta.second.dto.MsgResponseDto> updateShop(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long id, @RequestBody ShopRequestDto requestDto) {
        try {
            ShopResponseDto result = shopService.updateShop(id, requestDto, userDetails.getUser());
            return ResponseEntity.ok().body(new MsgResponseDto("가게 수정 성공",HttpStatus.OK.value()));
        } catch (RejectedExecutionException e) {
            return ResponseEntity.badRequest().body(new MsgResponseDto("가게 수정 실패", HttpStatus.BAD_REQUEST.value()));
        }
    }

}
