package com.sparta.second.entity;

import com.sparta.second.dto.ShopRequestDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.net.URL;
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

    @Column(name = "shopImage")
    private URL shopImage;


    @Column(name = "userName")
    private String userName;

    @OneToMany(mappedBy = "shop", orphanRemoval = true)
    private List<Menu> menus;

    @OneToMany(mappedBy = "shop", orphanRemoval = true)
    private List<Review> reviews;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    public Shop(ShopRequestDto requestDto, User user) {
        //this.shopCategory = requestDto.getCategory();
        this.shopName = requestDto.getShopName();
        this.shopContent = requestDto.getShopContent();
        this.shopImage = requestDto.getShopImage();
        this.userName = requestDto.getUserName();
        this.user = user;
    }

    public void update(ShopRequestDto requestDto) {
        this.shopName = requestDto.getShopName();
        this.shopContent = requestDto.getShopContent();
        this.shopImage = requestDto.getShopImage();
        this.userName = requestDto.getUserName();
    }

}
