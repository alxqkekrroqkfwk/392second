package com.sparta.second.service;

import com.sparta.second.dto.PasswordRequestDto;
import com.sparta.second.dto.ProfileUpdateDto;
import com.sparta.second.entity.User;
import com.sparta.second.repository.UserRepository;
import com.sparta.second.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public ProfileUpdateDto detailProfile (UserDetailsImpl userDetails) {
        ProfileUpdateDto profileUpdateDto = new ProfileUpdateDto(userDetails.getUser());
        return profileUpdateDto;
    }

    @Transactional
    public boolean checkPassword(UserDetailsImpl userDetails, PasswordRequestDto requestDto) {
        // 수정 페이지로 넘어가기 전 비밀번호 확인
        System.out.println(requestDto.getPassword());
        if (!requestDto.getPassword().equals(userDetails.getUser().getUserPassword())) {
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }
        return true;
    }

    @Transactional
    public ProfileUpdateDto updateProfile (ProfileUpdateDto profileUpdateDto, UserDetailsImpl userDetails) {
        User user = userDetails.getUser();
        user.setUserName(profileUpdateDto.getUserName());
        user.setUserPassword(profileUpdateDto.getUserPassword());
        user.setUserNick(profileUpdateDto.getUserNick());
        user.setMyContent(profileUpdateDto.getMyContent());
        return profileUpdateDto;
    }


}
