package com.junmo.junmowebsite.global.exception;

import com.junmo.junmowebsite.global.util.ResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionRestControllerAdvice {

    @ExceptionHandler
    public ResponseEntity<ResponseDto<Object>> serverException(RuntimeException e) {
        log.error(e.getMessage(), e);
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ResponseDto.response(e.getMessage() + " || 서버에러발생"));

    }

}
