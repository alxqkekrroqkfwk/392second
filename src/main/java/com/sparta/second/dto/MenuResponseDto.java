package com.sparta.second.dto;
import com.sparta.second.entity.Menu;
import lombok.Getter;

import java.net.URL;

@Getter
public class MenuResponseDto {

    private Long menu_id;
    private String menuTitle;
    private String menuCategory;
    private String menuContent;
    private URL menuImage;

    public MenuResponseDto(Menu menu) {
        this.menu_id = menu.getMenu_id();
        this.menuTitle = menu.getMenuTitle();
        this.menuCategory = menu.getMenuCategory();
        this.menuContent = menu.getMenuContent();
        this.menuImage = menu.getMenuImage();

    }

}
