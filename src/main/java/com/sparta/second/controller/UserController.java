package com.sparta.second.controller;

import com.sparta.second.dto.PasswordRequestDto;
import com.sparta.second.dto.ProfileUpdateDto;
import com.sparta.second.security.UserDetailsImpl;
import com.sparta.second.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserService userService;


    @GetMapping("/profile")
    public ProfileUpdateDto detailProfile (@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return userService.detailProfile(userDetails);
    }

    @PutMapping("/profile")
    public ProfileUpdateDto profileUpdateDto (@AuthenticationPrincipal UserDetailsImpl userDetails,
                                              @RequestBody ProfileUpdateDto profileUpdateDto,
                                              @RequestParam(value = "image", required = false) MultipartFile image,
                                              PasswordRequestDto requestDto) throws Exception {

        return userService.updateProfile(profileUpdateDto, userDetails, requestDto);
        // msg로 반환하기로 바꾸기!
        // MsgResponseDto를 ProfileUpdateDto로 감싼다
    }

    //프로필업데이트디티오 -> 로그인과 다른
    //User = profile 필요 정보가 같음

    // 1 비밀번호 검증, 인코더 확인 수정하기
    // 2 MsgResponseDto 반환해주기
    // 3 회원가입, 로그인 끝나면 이용해서 포스트맨 확인하기

}
