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
//    @Email
    private String userName;

    /*@JoinColumn(name = "user")
    private User user;*/

    /*@Column(name = "shopImage", nullable = false)
    private*/


    public Shop(ShopRequestDto requestDto, User id) {
        this.shopName = requestDto.getShopName();
        this.shopContent = requestDto.getShopContent();
        this.userName = requestDto.getUserName();
    }

    public void update(ShopRequestDto requestDto) {
        this.shopName = requestDto.getShopName();
        this.shopContent = requestDto.getShopContent();
        this.userName = requestDto.getUserName();
    }

}
