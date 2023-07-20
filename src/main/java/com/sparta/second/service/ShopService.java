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

    public Shop findShop(Long shopId) {
        return shopRepository.findById(shopId).orElseThrow(() ->
                new IllegalArgumentException("가게 조회 실패"));
    }

    @Transactional
    public void createShop(ShopRequestDto shopRequestDto) {
        shopRepository.save(new Shop(shopRequestDto));
    }

    // id 값 수정(username), 유효성 검사 수정
    @Transactional
    public void deleteShop(Long shopId, User user) {
        String id = findShop(shopId).getUser().getUserName();
        Shop shop = findShop(shopId);

        if (!id.equals(user.getUserName())) {
            throw new RejectedExecutionException();
        } else
            shopRepository.delete(shop);
    }

    @Transactional
    public ShopResponseDto updateShop(Long shopId, ShopRequestDto requestDto, User user) {
        String id = findShop(shopId).getUser().getUserName();
        Shop shop = findShop(shopId);

        if (!id.equals(user.getUserName())) {
            throw new RejectedExecutionException();
        }

        shop.setShopName(requestDto.getShopName());
        shop.setShopContent(requestDto.getShopContent());

        return new ShopResponseDto(shop);
    }

}
