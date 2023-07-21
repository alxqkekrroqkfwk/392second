package com.sparta.second.dto;

import java.util.List;

public class OrderListResponseDto {

    List<OrderResponseDto> orderList;

    public OrderListResponseDto(List<OrderResponseDto> orderList) {
        this.orderList = orderList;
    }
}
