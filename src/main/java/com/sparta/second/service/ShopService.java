package com.sparta.second.service;

import com.sparta.second.dto.ShopListResponseDto;
import com.sparta.second.dto.ShopRequestDto;
import com.sparta.second.dto.ShopResponseDto;
import com.sparta.second.entity.Shop;
import com.sparta.second.entity.User;
import com.sparta.second.repository.ShopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.*;
import java.util.List;
import java.util.concurrent.RejectedExecutionException;

@Service
@RequiredArgsConstructor
public class ShopService {

    private final ShopRepository shopRepository;

    public ShopListResponseDto getShops() {
        List<Shop> shopList = shopRepository.findAllByOrderByModifiedAtDesc();
        return new ShopListResponseDto(shopList.stream().map(ShopResponseDto::new).toList());
    }

    // get Content 단건조회 메서드 추가
    public ShopResponseDto getShop(Long shop_id) {
        Shop shop = findShop(shop_id);
        return new ShopResponseDto(shop);
    }

    @Transactional
    public void createShop(ShopRequestDto requestDto, User user) {
        shopRepository.save(new Shop(requestDto, user));
    }

    @Transactional
    public void updateShop(Long shop_id, ShopRequestDto requestDto, User user) {
        Shop shop = findShop(shop_id);

        if (!shop.getUser().getUserName().equals(user.getUserName())) {
            throw new RejectedExecutionException();
        }
        shop.update(requestDto);
    }

    // id 값 수정(username), 유효성 검사 수정
    @Transactional
    public void deleteShop(Long shop_id, User user) {
        Shop shop = findShop(shop_id);

        if (!shop.getUser().getUserName().equals(user.getUserName())) {
            throw new RejectedExecutionException();
        } else
            shopRepository.delete(shop);
    }

    public Shop findShop(Long shopId) {
        return shopRepository.findById(shopId).orElseThrow(() ->
                new IllegalArgumentException("가게 조회 실패"));
    }
}
