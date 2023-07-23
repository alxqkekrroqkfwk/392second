package com.sparta.second.dto;

import java.util.List;

public class MenuListResponseDto {
    public List<MenuResponseDto> menuList;

    public MenuListResponseDto(List<MenuResponseDto> menuList) {
        this.menuList = menuList;
    }
}
