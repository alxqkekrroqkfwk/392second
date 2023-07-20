package com.sparta.second.dto;


import lombok.Getter;
import lombok.Setter;
import java.net.URL;

@Getter
@Setter
public class ShopRequestDto {

    private String category;
    private String shopName;
    private String shopContent;
    private URL shopImage;
}
