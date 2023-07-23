package com.sparta.second.dto;


import com.sparta.second.entity.ShopCategory;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class
ShopRequestDto {

    private ShopCategory shopCategory;
    private String shopName;
    private String shopContent;
    private String  shopImage;
}
