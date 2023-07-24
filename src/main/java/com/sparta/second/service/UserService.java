package com.sparta.second.service;


import com.sparta.second.dto.*;
import com.sparta.second.entity.PasswordHistory;
import com.sparta.second.entity.User;
import com.sparta.second.jwt.JwtUtil;
import com.sparta.second.repository.PasswordHistoryRepository;
import com.sparta.second.repository.UserRepository;
import com.sparta.second.security.UserDetailsImpl;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final PasswordHistoryRepository passwordHistoryRepository;

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

        passwordHistoryRepository.save(new PasswordHistory(user, password));
    }

    public UserResponseDto detailProfile (UserDetailsImpl userDetails) {
        UserResponseDto userResponseDto = new UserResponseDto(userDetails.getUser());
        return userResponseDto;
    }

    @Transactional
    public ProfileUpdateDto updateProfile (ProfileUpdateDto profileUpdateDto, UserDetailsImpl userDetails) throws Exception {

        System.out.println(profileUpdateDto.getUserPassword() + "               " + userDetails.getPassword());

        if (!passwordEncoder.matches(profileUpdateDto.getUserPassword(), userDetails.getPassword())) {

            throw new IllegalArgumentException("비밀번호 인증에 실패했습니다.");
        }
        // 프로필 수정 시 비밀번호를 한 번 더 입력받는 과정

        List<PasswordHistory> passwordHistories = passwordHistoryRepository.findTop4ByUserOrderByCreatedAtDesc(userDetails.getUser());
        // 최근 3번 사용한 비밀번호 조회

            for (int i = 0; i <passwordHistories.size(); i++) {
                PasswordHistory passwordHistory = passwordHistories.get(i);
                // passwordHistories.get(i)를 통해 passwordHistories 리스트에서 i번째 인덱스에 위치한 PasswordHistory 객체를 가져옴
                if (passwordEncoder.matches(profileUpdateDto.getChangePassword(), passwordHistory.getUserPassword()))
                    throw new IllegalArgumentException("최근 3번 사용한 비밀번호는 사용할 수 없습니다.");
                // passwordHistory.getUserPassword()(암호화) 된 비밀번호가 userDetails.getPassword()(사용자가 입력한 비밀번호)와 같다면 throw를 보냄
            }

        // 최근 3번 사용한 비밀번호 제한하기

        profileUpdateDto.setChangePassword(passwordEncoder.encode(profileUpdateDto.getChangePassword()));
        // 받아온 비밀번호 암호화 시켜줌

        User targetUser = userRepository.findById(userDetails.getUser().getUserId()).orElseThrow(() -> new Exception ());
        // targetUser는 User클래스의 객체이다
        // userRepository에서 userId로 user를 한 개 찾아온다
        // userId는 현재 로그인 한 Id를 찾아와야 함으로 userDetails를 실행시킨다
        // 반환타입이 Optional이기 때문에 null 값에 대한 처리를 해주기 위해 orElseThrow(() -> new Exception ())으로 null 값인 경우 Exception을 던진다

        // DB에 있는 유저의 정보를 바꾸기 위해 User를 가져오고 userRepository 안에 id를 찾아와라

        System.out.println(profileUpdateDto.getChangePassword());

        targetUser.updateProfile(profileUpdateDto);

        passwordHistoryRepository.save(new PasswordHistory(userDetails.getUser(), profileUpdateDto.getChangePassword()));

        return profileUpdateDto;
    }


    public void login(LoginRequestDto loginRequestDto, HttpServletResponse res) {
        String username = loginRequestDto.getUserName();
        String password = loginRequestDto.getUserPassword();

        User user = userRepository.findByUserName(username).orElseThrow(
                () -> new IllegalArgumentException("등록된 사용자가 없습니다.")
        );

        if (!passwordEncoder.matches(password,user.getUserPassword())) {
            throw  new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        String token = jwtUtil.createToken(user.getUserName());
        jwtUtil.addJwtToCookie(token,res);
    }
    @Transactional
    public void moneyUpdate(User user ,MoneyRquestDto moneyRquestDto) {
        User user1 = userRepository.findByUserName(user.getUserName()).orElseThrow(
                () -> new IllegalArgumentException("등록된 사용자가 없습니다.")
        );
        user1.setMoney(moneyRquestDto.getMoney());
    }
}
