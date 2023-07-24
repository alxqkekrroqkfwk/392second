package com.sparta.second.entity;

import com.sparta.second.dto.OrderRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "orders")
public class Order extends TimeStamped{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @Column
    private String content;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @OneToMany(mappedBy = "order",fetch = FetchType.LAZY)
    private List<OrderMenu> ordermenuList = new ArrayList<>();

    @Column
    private int total;

    public Order(User user, List<OrderMenu> ordermenuList, OrderRequestDto orderRequestDto) {
        this.content = orderRequestDto.getContent();
        this.user = user;
        this.ordermenuList = ordermenuList;
        for (OrderMenu orderMenu : ordermenuList) {
            this.total += orderMenu.getMenu().getMenuPreice();
        }
    }

    public void update(OrderRequestDto orderRequestDto) {
        this.content = orderRequestDto.getContent();
    }
}
