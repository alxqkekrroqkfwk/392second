package com.sparta.second.repository;

import com.sparta.second.entity.OrderMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderMenuRepository extends JpaRepository<OrderMenu,Long> {
    List<OrderMenu> findAllBy();

}
