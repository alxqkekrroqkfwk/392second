package com.sparta.second.service;

import com.sparta.second.dto.OrderMenuListResponseDto;
import com.sparta.second.dto.OrderMenuRequestDto;
import com.sparta.second.dto.OrderMenuResponseDto;
import com.sparta.second.entity.Menu;
import com.sparta.second.entity.Order;
import com.sparta.second.entity.OrderMenu;
import com.sparta.second.entity.User;
import com.sparta.second.repository.MenuRepository;
import com.sparta.second.repository.OrderMenuRepository;
import com.sparta.second.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.RejectedExecutionException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderMenuService {

    private final OrderMenuRepository orderMenuRepository;
    private final MenuRepository menuRepository;

    public void create(User user, OrderMenuRequestDto orderMenuRequestDto) {
        Menu menu = menuRepository.findByMenuTitle(orderMenuRequestDto.getMenuTitle());

        orderMenuRepository.save(new OrderMenu(menu,user));
    }


    public OrderMenuResponseDto get(Long ordernemuId, User user) {
        OrderMenu orderMenu = findOrderMenu(ordernemuId);

        if (!orderMenu.getUser().getUserName().equals(user.getUserName())) {
            throw new RejectedExecutionException();
        }else {
            return new OrderMenuResponseDto(orderMenu);
        }
    }

    public OrderMenuListResponseDto gets(User user) {
        List<OrderMenu> orderMenuList = orderMenuRepository.findAllBy();
        OrderMenu orderMenu= orderMenuList.get(0);

        if(!orderMenu.getUser().getUserId().equals(user.getUserId())) {
            throw new RejectedExecutionException();
        }else {
            List<OrderMenuResponseDto> orderMenuResponseDtoList = orderMenuList
                    .stream().map(OrderMenuResponseDto::new).collect(Collectors.toList());
            return new OrderMenuListResponseDto(orderMenuResponseDtoList);
        }
    }

    @Transactional
    public void update(User user , OrderMenuRequestDto orderMenuRequestDto, Long ordermenuId) {
        OrderMenu orderMenu = findOrderMenu(ordermenuId);

        if (!orderMenu.getUser().getUserId().equals(user.getUserId())){
            throw new RejectedExecutionException();
        }
        Menu menu = menuRepository.findByMenuTitle(orderMenuRequestDto.getMenuTitle());
        orderMenu.updata(menu);
    }

    @Transactional
    public void delete(User user , Long ordermenuId) {
        OrderMenu orderMenu = findOrderMenu(ordermenuId);

        if (!orderMenu.getUser().getUserId().equals(user.getUserId())){
            throw new RejectedExecutionException();
        }
        orderMenuRepository.delete(orderMenu);
    }

    private OrderMenu findOrderMenu(Long ordernemuId) {
        return orderMenuRepository.findById(ordernemuId).orElseThrow(() ->
                new IllegalArgumentException("선택한 게시물이 없습니다."));
    }


}
