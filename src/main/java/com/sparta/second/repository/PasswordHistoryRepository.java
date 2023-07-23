package com.sparta.second.repository;

import com.sparta.second.entity.PasswordHistory;
import com.sparta.second.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PasswordHistoryRepository extends JpaRepository<PasswordHistory, Long> {
    List<PasswordHistory> findTop4ByUserOrderByCreatedAtDesc(User user);
    // PasswordHistory를 List로 불러오고 findTop3ByUserOrderByCreatedAtDesc 메서드를 실행
}