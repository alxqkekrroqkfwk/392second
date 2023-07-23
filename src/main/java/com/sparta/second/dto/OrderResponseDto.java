package com.sparta.second.dto;

import com.sparta.second.entity.Order;
import com.sparta.second.entity.OrderMenu;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class OrderResponseDto {
    private String content;
    private List<OrderMenuResponseDto> OrderList;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;
    public OrderResponseDto(Order order) {
        this.content = order.getContent();
        this.createdAt = order.getCreatedAt();
        this.modifiedAt = order.getModifiedAt();
        List<OrderMenuResponseDto> orderMenuResponseDtoList = new ArrayList<>();
        for (OrderMenu orderMenu : order.getOrdermenuList()) {
            OrderMenuResponseDto orderMenuResponseDto = new OrderMenuResponseDto(orderMenu);
            orderMenuResponseDtoList.add(orderMenuResponseDto);
        }
        this.OrderList = orderMenuResponseDtoList;
    }
}
