package com.sparta.second.dto;

import com.sparta.second.entity.Menu;
import com.sparta.second.entity.Order;

import java.time.LocalDateTime;
import java.util.List;

public class OrderResponseDto {
    private String content;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;
    public OrderResponseDto(Order order) {
        this.content = order.getContent();
        this.createdAt = order.getCreatedAt();
        this.modifiedAt = order.getModifiedAt();
    }
}
