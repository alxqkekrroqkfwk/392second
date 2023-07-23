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

    @PostMapping("/order")
    public ResponseEntity<MsgResponseDto> createOrder(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody OrderRequestDto orderRequestDto) {
        orderService.createOrder(userDetails.getUser(),orderRequestDto);
        return ResponseEntity.ok().body(new MsgResponseDto("오더 생성 완료 !", HttpStatus.OK.value()));
    }

    @PostMapping("/payment")
    public ResponseEntity<MsgResponseDto> payment(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        try {
            orderService.payment(userDetails.getUser());
            return ResponseEntity.ok().body(new MsgResponseDto("오더 주문 완료 !",HttpStatus.OK.value()));
        }catch (RejectedExecutionException e) {
            return ResponseEntity.badRequest().body(new MsgResponseDto("사용자의 금액이 부족합니다.", HttpStatus.BAD_REQUEST.value()));
        }
    }

    @GetMapping("/order/{order_id}")
    public ResponseEntity<OrderResponseDto> getOrder(@AuthenticationPrincipal UserDetailsImpl userDetails,@PathVariable Long order_id) {
        OrderResponseDto orderResponseDto = orderService.getOrder(order_id,userDetails.getUser());
        return ResponseEntity.ok().body(orderResponseDto);
    }

    @GetMapping("/orders")
    public ResponseEntity<OrderListResponseDto> getOrders() {
        OrderListResponseDto orderListResponseDto = orderService.getOrders();
        return ResponseEntity.ok().body(orderListResponseDto);
    }
    @Transactional
    @PutMapping("/order/{order_id}")
    public ResponseEntity<MsgResponseDto> update(@PathVariable Long review_Id,@AuthenticationPrincipal UserDetailsImpl userDetails,@RequestBody OrderRequestDto orderRequestDto) {
        orderService.update(review_Id,userDetails.getUser(),orderRequestDto);
        return ResponseEntity.ok().body(new MsgResponseDto("오더 수정 완료 !", HttpStatus.OK.value()));
    }

    @DeleteMapping("/order/{order_id}")
    public ResponseEntity<MsgResponseDto> delete(@PathVariable Long review_Id,@AuthenticationPrincipal UserDetailsImpl userDetails) {
        orderService.delete(review_Id,userDetails.getUser());
        return ResponseEntity.ok().body(new MsgResponseDto("오더 삭제 완료 !",HttpStatus.OK.value()));
    }
}
