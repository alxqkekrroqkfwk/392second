package com.sparta.second.entity;

import com.sparta.second.dto.ShopRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "shop")
@NoArgsConstructor

public class Shop extends TimeStamped{

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long shopId;

    @Column(name = "shopName", nullable = false)
    private String shopName;

    @Column(name = "shopContent", nullable = false, length = 500)
    private String shopContent;

    @Column
    @Enumerated(EnumType.STRING)
    private ShopCategory shopCategory;

    @Column
    private String shopImage;

    @OneToMany(mappedBy = "shop", orphanRemoval = true)
    private List<Menu> menus;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    public Shop(ShopRequestDto requestDto, User user) {
        this.shopContent = requestDto.getShopContent();
        this.shopName = requestDto.getShopName();
        this.shopImage = requestDto.getShopImage();
        this.shopCategory = requestDto.getShopCategory();
        this.user = user;
    }

    public void update(ShopRequestDto requestDto) {
        this.shopContent = requestDto.getShopContent();
        this.shopName = requestDto.getShopName();
        this.shopImage = requestDto.getShopImage();
        this.shopCategory = requestDto.getShopCategory();
    }

}
