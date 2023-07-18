package com.sparta.second.controller;

import com.sparta.second.dto.ProfileUpdateDto;
import com.sparta.second.security.UserDetailsImpl;
import com.sparta.second.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @PostMapping("/profile")
    public ProfileUpdateDto profileUpdateDto (@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody ProfileUpdateDto profileUpdateDto) {
        return userService.updateProfile(profileUpdateDto, userDetails);
    }

    //프로필업데이트디티오 -> 로그인과 다른
    //User = profile 필요 정보가 같음

}
