package com.sparta.second.dto;

import com.sparta.second.entity.Shop;
import lombok.Getter;

import java.net.URL;

@Getter
public class ShopResponseDto {

    private Long shopId;
    private String category;
    private String shopName;
    private String shopContent;
    private URL shopImage;

    public ShopResponseDto(Shop shop) {
        this.shopId = shop.getShopId();
        this.category = shop.getCategory();
        this.shopName = shop.getShopName();
        this.shopContent = shop.getShopContent();
        this.shopImage = shop.getShopImage();
    }
}
