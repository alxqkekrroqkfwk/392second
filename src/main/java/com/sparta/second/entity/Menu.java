package com.sparta.second.entity;

import com.sparta.second.dto.MenuRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.net.URL;

@Entity
@Getter
@Setter@Table(name = "menus")
@NoArgsConstructor
public class Menu extends TimeStamped{

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long menuId;

    @Column(name = "menuTitle", nullable = false)
    private String menuTitle;

    @Column(name = "menuPrice", nullable = false)
    private int menuPreice;

    @Column(name = "menuCategory", nullable = false)
    @Enumerated(EnumType.STRING)
    private MenuCategory menuCategory;

    @Column(name = "menuContent", nullable = false, length = 500)
    private String menuContent;

    @Column(name = "menuImage")
    private URL menuImage;

    @ManyToOne
    @JoinColumn(name = "shopId")
    private Shop shop;

    @OneToMany(mappedBy = "menu", orphanRemoval = true)
    private List<OrderMenu> order_menuList = new ArrayList<>();

    public Menu(MenuRequestDto menuRequestDto,Shop shop){
        this.menuTitle = menuRequestDto.getMenuTitle();
        this.menuPreice = menuRequestDto.getMenuPrice();
        this.menuCategory = menuRequestDto.getMenuCategory();
        this.menuContent = menuRequestDto.getMenuContent();
        this.menuImage = menuRequestDto.getMenuImage();
        this.shop = shop;
    }

    public void update(MenuRequestDto menuRequestDto) {
        this.menuTitle = menuRequestDto.getMenuTitle();
        this.menuPreice = menuRequestDto.getMenuPrice();
        this.menuCategory = menuRequestDto.getMenuCategory();
        this.menuContent = menuRequestDto.getMenuContent();
        this.menuImage = menuRequestDto.getMenuImage();
    }
}
