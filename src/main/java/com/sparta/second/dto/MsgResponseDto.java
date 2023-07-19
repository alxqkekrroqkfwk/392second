package com.sparta.second.dto;

public class MsgResponseDto {

    private String msg;
    private Integer statusCode;

    public MsgResponseDto(String msg, Integer statusCode) {
        this.msg = msg;
        this.statusCode = statusCode;
    }
}
