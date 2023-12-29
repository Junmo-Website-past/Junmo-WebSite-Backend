package com.junmo.junmowebsite.global.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    // 게시판
    BOARD_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 게시판입니다.");


    private HttpStatus httpStatus;
    private String message;

    ErrorCode(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }
}
