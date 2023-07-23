package com.sparta.second.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class OrderMenuListResponseDto {
    public List<OrderMenuResponseDto> ordermenuList;

    public OrderMenuListResponseDto(List<OrderMenuResponseDto> ordermenuList) {
        this.ordermenuList = ordermenuList;
    }
}
