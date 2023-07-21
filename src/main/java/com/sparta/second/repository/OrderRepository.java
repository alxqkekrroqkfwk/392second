package com.sparta.second.repository;

import com.sparta.second.entity.Order;
import com.sparta.second.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
    List<Order> findAllByOrderByModifiedAtDesc();
}
