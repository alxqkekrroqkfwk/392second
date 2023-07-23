package com.sparta.second.repository;

import com.sparta.second.dto.ShopResponseDto;
import com.sparta.second.entity.Shop;
import com.sparta.second.dto.ShopResponseDto;
import com.sparta.second.entity.Shop;
import com.sparta.second.entity.ShopCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ShopRepository extends JpaRepository<Shop, Long> {
    List<Shop> findAllByOrderByModifiedAtDesc();

    List<Shop> findByShopCategory(ShopCategory shopCategory);
}
