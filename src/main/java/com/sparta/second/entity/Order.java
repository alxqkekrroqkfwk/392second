package com.sparta.second.entity;

import com.sparta.second.dto.OrderRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "orders")
public class Order extends TimeStamped{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long order_id;

    @Column
    private String content;

    @OneToMany
    private List<Menu> menus;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Order(OrderRequestDto orderRequestDto,User user) {
        this.content = orderRequestDto.getContent();
        this.menus = orderRequestDto.getMenus();
        this.user = user;
    }

    public void update(OrderRequestDto orderRequestDto) {
        this.content = orderRequestDto.getContent();
        this.menus = orderRequestDto.getMenus();
    }
}
