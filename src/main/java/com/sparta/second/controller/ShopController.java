package com.sparta.second.controller;

import com.sparta.second.dto.*;
import com.sparta.second.entity.ShopCategory;
import com.sparta.second.security.UserDetailsImpl;
import com.sparta.second.service.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<ShopResponseDto> getShop(@PathVariable Long shopId){
        ShopResponseDto responseDto = shopService.getShop(shopId);
        return ResponseEntity.ok().body(responseDto);
    }

    @GetMapping("/shop/category")
    public ResponseEntity<ShopListResponseDto> getShopCategory(@RequestParam ShopCategory shopCategory) {
        ShopListResponseDto shopListResponseDto=shopService.getShopCategory(shopCategory);
        return ResponseEntity.ok().body(shopListResponseDto);
    }

    @PostMapping("/shop")
    public ResponseEntity<MsgResponseDto> createShop(@RequestBody ShopRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        shopService.createShop(requestDto,userDetails.getUser());
        return ResponseEntity.ok().body(new MsgResponseDto("가게 생성 완료 !",HttpStatus.OK.value()));
    }

    @PutMapping("/shop/{shop_id}")
    public ResponseEntity<MsgResponseDto> updateShop(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long shop_id, @RequestBody ShopRequestDto requestDto) {
        try {
            shopService.updateShop(shop_id, requestDto, userDetails.getUser());
            return ResponseEntity.ok().body(new MsgResponseDto("가게 수정 성공", HttpStatus.OK.value()));
        } catch (RejectedExecutionException e) {
            return ResponseEntity.badRequest().body(new MsgResponseDto("가게 수정 실패", HttpStatus.BAD_REQUEST.value()));
        }
    }
    @DeleteMapping("/shop/{shop_id}")
    public ResponseEntity<MsgResponseDto> deleteShop (@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long shop_id){
        try {
            shopService.deleteShop(shop_id, userDetails.getUser());
            return ResponseEntity.ok().body(new MsgResponseDto("가게 삭제 성공", HttpStatus.OK.value()));
        } catch (RejectedExecutionException e) {
            return ResponseEntity.badRequest().body(new MsgResponseDto("가게 삭제 실패", HttpStatus.BAD_REQUEST.value()));
        }
    }
}
