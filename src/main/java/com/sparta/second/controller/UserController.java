package com.sparta.second.controller;

import com.sparta.second.requestDto.MsgResponseDto;
import com.sparta.second.requestDto.signupRequestDto;
import com.sparta.second.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    // 회원가입
    @PostMapping("/signup")
    public ResponseEntity<MsgResponseDto> signup(@RequestBody signupRequestDto requestDto){
        userService.signup(requestDto);
        return ResponseEntity.ok().body(new MsgResponseDto("회원가입 성공", HttpStatus.CREATED.value()));
    }

}
