package com.sparta.second.service;


import com.sparta.second.dto.OrderListResponseDto;
import com.sparta.second.dto.OrderRequestDto;
import com.sparta.second.dto.OrderResponseDto;
import com.sparta.second.entity.Order;
import com.sparta.second.entity.User;
import com.sparta.second.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.RejectedExecutionException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    @Transactional
    public void createOrder(User user, OrderRequestDto orderRequestDto) {
        Order order = new Order(orderRequestDto,user);
        System.out.println(order.getUser().getUserName());
        System.out.println(user.getUserName());
        if (!order.getUser().getUserName().equals(user.getUserName())) {
            throw new RejectedExecutionException();
        } else {
            orderRepository.save(order);
        }
    }

    public OrderResponseDto getOrder(Long orderId, User user) {
        Order order = findOrder(orderId);
        if (!order.getUser().getUserName().equals(user.getUserName())) {
            throw new RejectedExecutionException();
        }else {
            return new OrderResponseDto(order);
        }
    }

    public OrderListResponseDto getOrders() {
        List<OrderResponseDto> orderList = orderRepository.findAllByOrderByModifiedAtDesc()
                .stream().map(OrderResponseDto::new).collect(Collectors.toList());

        return new OrderListResponseDto(orderList);
    }

    public void update(Long orderId,User user,OrderRequestDto orderRequestDto) {
         Order order = findOrder(orderId);

         if (!order.getUser().getUserName().equals(user.getUserName())) {
             throw new RejectedExecutionException();
         }else {
             order.update(orderRequestDto);
         }
    }

    public void delete(Long orderId,User user) {
        Order order = findOrder(orderId);
        if (order.getUser().getUserName().equals(user.getUserName())) {
            throw new RejectedExecutionException();
        }else {
            orderRepository.delete(order);
        }
    }

    public Order findOrder(Long orderId) {
        return orderRepository.findById(orderId).orElseThrow(() ->
                new IllegalArgumentException("선택한 게시물이 없습니다."));
    }
}
