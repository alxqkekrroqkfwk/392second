package com.sparta.second.controller;

import com.sparta.second.dto.MsgResponseDto;
import com.sparta.second.dto.OrderMenuListResponseDto;
import com.sparta.second.dto.OrderMenuRequestDto;
import com.sparta.second.dto.OrderMenuResponseDto;
import com.sparta.second.security.UserDetailsImpl;
import com.sparta.second.service.OrderMenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api")
public class OrderMenuController {

    private final OrderMenuService orderMenuService;

    @PostMapping("/ordermenu")
    public ResponseEntity<MsgResponseDto> create(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody OrderMenuRequestDto orderMenuRequestDto) {
        orderMenuService.create(userDetails.getUser(),orderMenuRequestDto);
        return ResponseEntity.ok().body(new MsgResponseDto("주문목록 생성 완료 !", HttpStatus.OK.value()));
    }

    @GetMapping("/ordermenu/{ordernemuId}")
    public ResponseEntity<OrderMenuResponseDto> get(@PathVariable Long ordernemuId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        OrderMenuResponseDto orderMenuResponseDto=orderMenuService.get(ordernemuId,userDetails.getUser());
        return ResponseEntity.ok().body(orderMenuResponseDto);
    }

    @GetMapping("/ordermenus")
    public ResponseEntity<OrderMenuListResponseDto> gets(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        OrderMenuListResponseDto orderMenuListResponseDto =orderMenuService.gets(userDetails.getUser());
        return ResponseEntity.ok().body(orderMenuListResponseDto);
    }

    @PutMapping("/ordermenu/{ordermenuId}")
    public ResponseEntity<MsgResponseDto> updata(@AuthenticationPrincipal UserDetailsImpl userDetails,@RequestBody OrderMenuRequestDto orderMenuRequestDto , @PathVariable Long ordermenuId) {
        orderMenuService.update(userDetails.getUser(),orderMenuRequestDto,ordermenuId);
        return ResponseEntity.ok().body(new MsgResponseDto("주문목록 수정 완료 !",HttpStatus.OK.value()));
    }

    @DeleteMapping("/ordermenu/{ordermenuId}")
    public ResponseEntity<MsgResponseDto> delete(@AuthenticationPrincipal UserDetailsImpl userDetails,@PathVariable Long ordermenuId) {
        orderMenuService.delete(userDetails.getUser(),ordermenuId);
        return ResponseEntity.ok().body(new MsgResponseDto("주문목록 삭제 완료 !",HttpStatus.OK.value()));
    }
}
