package com.sparta.second.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Entity
@Setter
@NoArgsConstructor
public class PasswordHistory extends TimeStamped{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private String userPassword;

//    public PasswordHistory(User user, String userPassword) {
//        this.user = user;
//        this.userPassword = userPassword;
//    }
    // PasswordHistory 엔티티 클래스는 유저의 비밀번호 변경 이력을 저장
}
