package com.sparta.second.dto;

import com.sparta.second.entity.OrderMenu;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class OrderMenuResponseDto {
    private String menuTitle;

    public OrderMenuResponseDto(OrderMenu orderMenu) {
        this.menuTitle = orderMenu.getMenu().getMenuTitle();
    }
}
