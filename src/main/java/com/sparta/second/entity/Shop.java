package com.sparta.second.entity;

import com.sparta.second.dto.ShopRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.net.URL;

@Getter
@Setter
@Entity
@Table(name = "shop")
@NoArgsConstructor

public class Shop {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long shopId;

    @Column(name = "shopName", nullable = false)
    private String shopName;

    @Column(name = "shopContent", nullable = false, length = 500)
    private String shopContent;

    @Column(name = "shopImage")
    private URL shopImage;

    @Column(name = "category")
    private String category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin")
    private User user;


    public Shop(ShopRequestDto requestDto) {
        this.category = requestDto.getCategory();
        this.shopName = requestDto.getShopName();
        this.shopContent = requestDto.getShopContent();
        this.shopImage = requestDto.getShopImage();
    }

    public void update(ShopRequestDto requestDto) {
        this.category = requestDto.getCategory();
        this.shopName = requestDto.getShopName();
        this.shopContent = requestDto.getShopContent();
        this.shopImage = requestDto.getShopImage();
    }


}
