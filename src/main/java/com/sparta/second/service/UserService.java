package com.sparta.second.service;

import com.sparta.second.entity.User;
import com.sparta.second.repository.UserRepository;
import com.sparta.second.dto.signupRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // 회원가입
    public void signup(signupRequestDto requestDto) {
        String username = requestDto.getUserName();
        String password = passwordEncoder.encode(requestDto.getUserPassword());
        String email = requestDto.getUserEmail();

        if(userRepository.findByUserName(username).isPresent()){
            throw new IllegalArgumentException("중복된 이름입니다.");
        }

        User user = new User(username, password,email);
        userRepository.save(user);
    }
}
