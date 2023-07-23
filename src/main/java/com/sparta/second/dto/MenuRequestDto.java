package com.sparta.second.dto;

import com.sparta.second.entity.MenuCategory;
import lombok.Getter;
import lombok.Setter;
import java.net.URL;
@Getter
@Setter
public class MenuRequestDto {

    private String menuTitle;
    private int menuPrice;
    private MenuCategory menuCategory;
    private String menuContent;
    private URL menuImage;
}
