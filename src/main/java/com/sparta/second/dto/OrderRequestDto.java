package com.sparta.second.dto;

import com.sparta.second.entity.Menu;
import lombok.Getter;

import java.util.List;

@Getter
public class OrderRequestDto {
    private String content;

    private List<Menu> menus;
}
