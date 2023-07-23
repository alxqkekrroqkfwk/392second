package com.sparta.second.controller;

import com.sparta.second.dto.*;
import com.sparta.second.security.UserDetailsImpl;
import com.sparta.second.service.OrderService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.RejectedExecutionException;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/payment")
    public ResponseEntity<MsgResponseDto> payment(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        try {
            orderService.payment(userDetails.getUser());
            return ResponseEntity.ok().body(new MsgResponseDto("오더 주문 완료 !",HttpStatus.OK.value()));
        }catch (RejectedExecutionException e) {
            return ResponseEntity.badRequest().body(new MsgResponseDto("사용자의 금액이 부족합니다.", HttpStatus.BAD_REQUEST.value()));
        }
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<OrderResponseDto> getOrder(@AuthenticationPrincipal UserDetailsImpl userDetails,@PathVariable Long orderId) {
        OrderResponseDto orderResponseDto = orderService.getOrder(orderId,userDetails.getUser());
        return ResponseEntity.ok().body(orderResponseDto);
    }

    @GetMapping("/orders")
    public ResponseEntity<OrderListResponseDto> getOrders() {
        OrderListResponseDto orderListResponseDto = orderService.getOrders();
        return ResponseEntity.ok().body(orderListResponseDto);
    }
    @Transactional
    @PutMapping("/order/{orderId}")
    public ResponseEntity<MsgResponseDto> update(@PathVariable Long orderId,@AuthenticationPrincipal UserDetailsImpl userDetails,@RequestBody OrderRequestDto orderRequestDto) {
        orderService.update(orderId,userDetails.getUser(),orderRequestDto);
        return ResponseEntity.ok().body(new MsgResponseDto("오더 수정 완료 !", HttpStatus.OK.value()));
    }

    @Transactional
    @DeleteMapping("/order/{orderId}")
    public ResponseEntity<MsgResponseDto> delete(@PathVariable Long orderId,@AuthenticationPrincipal UserDetailsImpl userDetails) {
        orderService.delete(orderId,userDetails.getUser());
        return ResponseEntity.ok().body(new MsgResponseDto("오더 삭제 완료 !",HttpStatus.OK.value()));
    }
}

