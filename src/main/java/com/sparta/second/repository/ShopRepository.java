package com.sparta.second.repository;

import com.sparta.second.dto.ShopResponseDto;
import com.sparta.second.entity.Shop;
import com.sparta.second.dto.ShopResponseDto;
import com.sparta.second.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ShopRepository extends JpaRepository<Shop, Long> {
    List<Shop> findAllByOrderByModifiedAtDesc();
}
