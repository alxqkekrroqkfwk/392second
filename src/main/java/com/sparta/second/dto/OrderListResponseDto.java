package com.sparta.second.dto;

import lombok.Getter;

import java.util.List;
@Getter
public class OrderListResponseDto {

    List<OrderResponseDto> orderList;

    public OrderListResponseDto(List<OrderResponseDto> orderList) {
        this.orderList = orderList;
    }
}
