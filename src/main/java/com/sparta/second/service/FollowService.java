package com.sparta.second.service;

import com.sparta.second.entity.Follow;
import com.sparta.second.entity.Shop;
import com.sparta.second.entity.User;
import com.sparta.second.repository.FollowRepository;
import com.sparta.second.repository.ShopRepository;
import com.sparta.second.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FollowService {

    ShopRepository shopRepository;
    UserRepository userRepository;
    FollowRepository followRepository;

    @Transactional
    public String followShop(Long id) {
        Shop shop = shopRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 가게가 존재하지 않습니다."));

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUserName(authentication.getName()).orElseThrow(() ->
                new IllegalArgumentException("선택한 유저가 존재하지 않습니다."));

        if(followRepository.findByShopAndUser(shop, user) == null) {
            // 팔로우를 누른적 없다면 Favorite 생성 후, 즐겨찾기 처리
            Follow follow = new Follow(shop, user); // true 처리
            followRepository.save(follow);
            return "팔로우 완료";
        } else {
            // 팔로우 누른적 있다면 즐겨찾기 처리 후 테이블 삭제
            Follow follow = followRepository.findFollowByBoard(shop);
            follow.unFavoriteBoard(shop);
            followRepository.delete(follow);
            return "언팔로우 완료";
        }
    }
}
