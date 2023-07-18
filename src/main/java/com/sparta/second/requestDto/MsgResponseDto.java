package com.sparta.second.requestDto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

// 메세지와 코드 반환용
@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MsgResponseDto {

    private String msg;
    private Integer statusCode;

    public MsgResponseDto(String msg, Integer statusCode){
        this.msg = msg;
        this.statusCode = statusCode;
    }

}
