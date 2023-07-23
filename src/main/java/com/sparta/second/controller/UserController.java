package com.sparta.second.controller;

import com.sparta.second.dto.*;
import com.sparta.second.security.UserDetailsImpl;
import com.sparta.second.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    // 회원가입
    @PostMapping("/signup")
    public ResponseEntity<MsgResponseDto> signup(@RequestBody SignupRequestDto requestDto){
        userService.signup(requestDto);
        return ResponseEntity.ok().body(new MsgResponseDto("회원가입 성공", HttpStatus.CREATED.value()));
    }

    @PostMapping("/login")
    public ResponseEntity<MsgResponseDto> login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse res) {
        try {
            userService.login(loginRequestDto,res);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().body(new MsgResponseDto("로그인 성공",HttpStatus.OK.value()));
    }


    @GetMapping("/profile")
    public ProfileUpdateDto detailProfile (@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return userService.detailProfile(userDetails);
    }

    @Transactional
    @PutMapping("/profile")
    public ResponseEntity<MsgResponseDto> profileUpdateDto (@AuthenticationPrincipal UserDetailsImpl userDetails,
                                              @RequestBody ProfileUpdateDto profileUpdateDto,
                                              @RequestParam(value = "image", required = false) MultipartFile image,
                                              PasswordRequestDto requestDto) throws Exception {

        userService.updateProfile(profileUpdateDto, userDetails);

        return ResponseEntity.ok().body(new MsgResponseDto("로그인 성공",HttpStatus.OK.value()));
        // msg로 반환하기로 바꾸기!
        // MsgResponseDto를 ProfileUpdateDto로 감싼다
    }

    @Transactional
    @PutMapping("/money")
    public ResponseEntity<MsgResponseDto> moneyUpdate(@AuthenticationPrincipal UserDetailsImpl userDetails,@RequestBody MoneyRquestDto moneyRquestDto) {
        userService.moneyUpdate(userDetails.getUser(),moneyRquestDto);
        return ResponseEntity.ok().body(new MsgResponseDto("돈 충전 완료 !",HttpStatus.OK.value()));
    }


    //프로필업데이트디티오 -> 로그인과 다른
    //User = profile 필요 정보가 같음

    // 1 비밀번호 검증, 인코더 확인 수정하기
    // 2 MsgResponseDto 반환해주기
    // 3 회원가입, 로그인 끝나면 이용해서 포스트맨 확인하기

}
