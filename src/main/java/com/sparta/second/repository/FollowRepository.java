package com.sparta.second.repository;

import com.sparta.second.entity.Follow;
import com.sparta.second.entity.Shop;
import com.sparta.second.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowRepository extends JpaRepository<Follow, Integer> {
    Follow findByShopAndUser(Shop shop, User user);
    Follow findFollowByBoard(Shop shop);
}
