package com.sparta.second.entity;

import com.sparta.second.dto.MenuRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.net.URL;

@Entity
@Getter
@Setter
@Table(name = "menus")
@NoArgsConstructor
public class Menu {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long menu_id;

    @Column(name = "menuTitle", nullable = false)
    private String menuTitle;

    @Column(name = "menuCategory", nullable = false)
    private String menuCategory;

    @Column(name = "menuContent", nullable = false, length = 500)
    private String menuContent;

    @Column(name = "menuImage")
    private URL menuImage;

    public Menu(MenuRequestDto requestDto){
        this.menuTitle = requestDto.getMenuTitle();
        this.menuCategory = requestDto.getMenuCategory();
        this.menuContent = requestDto.getContent();
        this.menuImage = requestDto.getMenuImage();
    }
}