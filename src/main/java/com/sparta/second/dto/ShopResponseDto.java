package com.sparta.second.dto;

import com.sparta.second.entity.Shop;
import com.sparta.second.entity.ShopCategory;
import lombok.Getter;

import java.net.URL;
import java.time.LocalDateTime;

@Getter
public class ShopResponseDto {

    private Long shopId;
    private ShopCategory category;
    private String shopName;
    private String shopContent;
    private String shopImage;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public ShopResponseDto(Shop shop) {
        this.shopId = shop.getShopId();
        this.shopName = shop.getShopName();
        this.shopContent = shop.getShopContent();
        this.shopImage = shop.getShopImage();
        this.category = shop.getShopCategory();
        this.createdAt = shop.getCreatedAt();
        this.modifiedAt = shop.getModifiedAt();
    }
}
