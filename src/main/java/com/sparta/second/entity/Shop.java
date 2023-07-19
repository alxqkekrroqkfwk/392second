package com.sparta.second.entity;

import com.sparta.second.dto.ShopRequestDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    public Shop(ShopRequestDto requestDto, User user) {
        this.shopName = requestDto.getShopName();
        this.shopContent = requestDto.getShopContent();
        this.userName = requestDto.getUserName();
        this.user = user;
    }

    public void update(ShopRequestDto requestDto) {
        this.shopName = requestDto.getShopName();
        this.shopContent = requestDto.getShopContent();
        this.userName = requestDto.getUserName();
    }

}
