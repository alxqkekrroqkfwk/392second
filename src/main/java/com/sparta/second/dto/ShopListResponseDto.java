package com.sparta.second.dto;

import com.sparta.second.entity.Shop;
import lombok.Getter;

import java.util.List;

@Getter
public class ShopListResponseDto {

    private List<ShopResponseDto> shopList;

    public ShopListResponseDto(List<ShopResponseDto> shopList) {
        this.shopList = shopList;
    }
}