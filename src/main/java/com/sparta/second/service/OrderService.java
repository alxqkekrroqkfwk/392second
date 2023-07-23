package com.sparta.second.service;


import com.sparta.second.dto.OrderListResponseDto;
import com.sparta.second.dto.OrderRequestDto;
import com.sparta.second.dto.OrderResponseDto;
import com.sparta.second.entity.Order;
import com.sparta.second.entity.OrderMenu;
import com.sparta.second.entity.User;
import com.sparta.second.repository.OrderMenuRepository;
import com.sparta.second.repository.OrderRepository;
import com.sparta.second.repository.UserRepository;
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
    private final OrderMenuRepository orderMenuRepository;
    private final UserRepository userRepository;

    @Transactional
    public void payment(User user) {
        List<OrderMenu> orderMenuList = orderMenuRepository.findAllBy();
        Order order = new Order(user,orderMenuList);
        if (user.getMoney()<order.getTotal()) {
            throw new RejectedExecutionException();
        }
        User user1 = userRepository.findByUserName(user.getUserName())
                .orElseThrow( () -> new IllegalArgumentException("등록된 사용자가 없습니다.")
        );
        user1.setMoney(user.getMoney()-order.getTotal());
        orderRepository.save(order);
    }

    public OrderResponseDto getOrder(Long orderId, User user) {
        Order order = findOrder(orderId);
        List<OrderMenu> orderMenuList = orderMenuRepository.findAllBy();
        order.setOrdermenuList(orderMenuList);
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

    @Transactional
    public void update(Long orderId,User user,OrderRequestDto orderRequestDto) {
         Order order = findOrder(orderId);

         if (!order.getUser().getUserName().equals(user.getUserName())) {
             throw new RejectedExecutionException();
         }else {
             order.update(orderRequestDto);
         }
    }

    @Transactional
    public void delete(Long orderId,User user) {
        Order order = findOrder(orderId);
        if (!order.getUser().getUserId().equals(user.getUserId())) {
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
