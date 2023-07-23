package com.sparta.second.repository;

import com.sparta.second.entity.Menu;
import com.sparta.second.entity.MenuCategory;
import com.sparta.second.entity.Shop;
import com.sparta.second.entity.ShopCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {
    List<Menu> findAllByOrderByModifiedAtDesc();

    Menu findByMenuTitle(String memuTitle);

    List<Menu> findByShop_ShopIdAndMenuCategory(Long shopId,MenuCategory menuCategory);
}
