package com.sparta.second.service;


import com.sparta.second.dto.*;
import com.sparta.second.entity.User;
import com.sparta.second.jwt.JwtUtil;
import com.sparta.second.repository.UserRepository;
import com.sparta.second.security.UserDetailsImpl;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;


    // 회원가입
    public void signup(SignupRequestDto requestDto) {
        String username = requestDto.getUserName();
        String password = passwordEncoder.encode(requestDto.getUserPassword());
        String email = requestDto.getUserEmail();
        String Nick = requestDto.getUserNick();
        UserRoleEnum role = requestDto.getRole();

        if(userRepository.findByUserName(username).isPresent()){
            throw new IllegalArgumentException("중복된 이름입니다.");
        }

        User user = new User(username, password,email, Nick, role);
        userRepository.save(user);
    }

    public ProfileUpdateDto detailProfile (UserDetailsImpl userDetails) {
        ProfileUpdateDto profileUpdateDto = new ProfileUpdateDto(userDetails.getUser());
        return profileUpdateDto;
    }

    @Transactional
    public ProfileUpdateDto updateProfile (ProfileUpdateDto profileUpdateDto, UserDetailsImpl userDetails) throws Exception {

        if (!passwordEncoder.matches(profileUpdateDto.getUserPassword(), userDetails.getUser().getUserPassword())) {
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }
        // 수정 페이지로 넘어가기 전 비밀번호 확인

        User targetUser = userRepository.findById(userDetails.getUser().getId()).orElseThrow(() -> new Exception ());
        // targetUser는 User클래스의 객체이다
        // userRepository에서 userId로 user를 한 개 찾아온다
        // userId는 현재 로그인 한 Id를 찾아와야 함으로 userDetails를 실행시킨다
        // 반환타입이 Optional이기 때문에 null 값에 대한 처리를 해주기 위해 orElseThrow(() -> new Exception ())으로 null 값인 경우 Exception을 던진다

        // DB에 있는 유저의 정보를 바꾸기 위해 User를 가져오고 userRepository 안에 id를 찾아와라

        targetUser.updateProfile(profileUpdateDto);

        return profileUpdateDto;
        // 수정완료  MsgResponseDto로 반환해야 함
    }


    public void login(LoginRequestDto loginRequestDto, HttpServletResponse res) {
        String username = loginRequestDto.getUserName();
        String password = loginRequestDto.getPassword();

        User user = userRepository.findByUserName(username).orElseThrow(
                () -> new IllegalArgumentException("등록된 사용자가 없습니다.")
        );

        if (!passwordEncoder.matches(password,user.getUserPassword())) {
            throw  new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        String token = jwtUtil.createToken(user.getUserName());
        jwtUtil.addJwtToCookie(token,res);
    }
}
