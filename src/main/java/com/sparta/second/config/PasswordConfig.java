package com.sparta.second.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class PasswordConfig { // Bean으로 저장될 때 -> passwordConfig

    @Bean
    public PasswordEncoder passwordEncoder() { // passwordEncoder -> Bean저장
        return new BCryptPasswordEncoder(); // BCrypt : Hash함수 -> 비밀번호를 암호화
    }
}