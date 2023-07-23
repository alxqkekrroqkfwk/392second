package com.sparta.second.dto;
import com.sparta.second.entity.Menu;
import com.sparta.second.entity.MenuCategory;
import lombok.Getter;

import java.net.URL;
import java.time.LocalDateTime;

@Getter
public class MenuResponseDto {

    private Long menu_id;
    private String menuTitle;
    private MenuCategory menuCategory;
    private String menuContent;
    private URL menuImage;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    public MenuResponseDto(Menu menu) {
        this.menu_id = menu.getMenuId();
        this.menuTitle = menu.getMenuTitle();
        this.menuCategory = menu.getMenuCategory();
        this.menuContent = menu.getMenuContent();
        this.menuImage = menu.getMenuImage();
        this.createdAt = menu.getCreatedAt();
        this.modifiedAt = menu.getModifiedAt();

    }

}
