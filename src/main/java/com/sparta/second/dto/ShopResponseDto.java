package com.sparta.second.dto;

import com.sparta.second.entity.Shop;
import lombok.Getter;

@Getter
public class ShopResponseDto {

    private Long shopId;
    private String shopName;
    private String shopContent;

    public ShopResponseDto(Shop shop) {
        this.shopId = shop.getShopId();
        this.shopName = shop.getShopName();
        this.shopContent = shop.getShopContent();
    }
}
