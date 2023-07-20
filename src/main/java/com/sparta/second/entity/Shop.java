package com.sparta.second.entity;

import com.sparta.second.dto.ShopRequestDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
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

    @Column(name = "userName")
    private String userName;

    @OneToMany(mappedBy = "shop", orphanRemoval = true)
    private List<Menu> menus;

    @OneToMany(mappedBy = "shop", orphanRemoval = true)
    private List<Review> reviews;

    @ManyToOne
    @JoinColumn(name = "id")
    private User user;


    public Shop(ShopRequestDto requestDto, User user) {
        this.shopName = requestDto.getShopName();
        this.shopContent = requestDto.getShopContent();
        this.user = user;
    }

    public void update(ShopRequestDto requestDto) {
        this.shopName = requestDto.getShopName();
        this.shopContent = requestDto.getShopContent();
    }


}
