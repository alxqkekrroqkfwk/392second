package com.sparta.second.dto;

import com.sparta.second.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ProfileUpdateDto {
    private String userEmail;
    private String userPassword;
    private String userNick;
    private String myContent;

    public ProfileUpdateDto(User user) {
        this.userEmail = user.getUserEmail();
        this.userPassword = user.getUserPassword();
        this.userNick = user.getUserNick();
        this.myContent = user.getMyContent();
    }

    // updateDto 사용?
    // 수정 할 때 재확인 & 수정하면 바뀌는 기능이 updateDto에 같이 있어야 하는?
    // 수정 할 때 재확인 -> 서비스 클래스에서 if문 조건으로 걸어주고? 그러는 건가요?
    // 프로필 수정을 하기 전에 비밀번호를 검증하는 코드는 어디에 있어야 하나요?
    // -> 비밀번호 검증하는 메서드를 먼저 따로 만들기!
    // -> 컨트롤러, 서비스, 레포지토리 구현 방식~
    // 둘 다 요청을 따로 받아도 되고
}
