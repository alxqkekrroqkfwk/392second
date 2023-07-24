package com.sparta.second.entity;

import com.sparta.second.dto.OrderMenuRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "ordermenu")
public class OrderMenu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ordermenuId;

    @ManyToOne
    @JoinColumn(name = "orderId")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "menuId")
    private Menu menu;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    public OrderMenu(Menu menu, User user) {
        this.menu = menu;
        this.user = user;
    }

    public void updata(Menu menu) {
        this.menu = menu;
    }
}
