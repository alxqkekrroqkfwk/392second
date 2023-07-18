package com.sparta.second.repository;

import com.sparta.second.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Long> {
    List<Menu> findAllByOrderByModifiedAtDesc();

}
