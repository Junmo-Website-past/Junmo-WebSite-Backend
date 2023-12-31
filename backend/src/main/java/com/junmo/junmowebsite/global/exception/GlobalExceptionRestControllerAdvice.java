package com.junmo.junmowebsite.global.exception;

import com.junmo.junmowebsite.global.util.ResponseDto;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionRestControllerAdvice {

    // 전체 에러 처리
    @ExceptionHandler
    public ResponseEntity<ResponseDto<Object>> serverException(RuntimeException e) {
        log.error(e.getMessage(), e);
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ResponseDto.response(e.getMessage() + " || 서버에러발생"));

    }

    // Valid 예외 처리
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDto> boardValidException(
        MethodArgumentNotValidException e
    ) {
        List<String> errors = new ArrayList<>();

        e.getBindingResult().getFieldErrors().forEach((error) -> {
            String errorMessage = error.getField() + " : " + error.getDefaultMessage();
            errors.add(errorMessage);
            log.error(errorMessage);
        });

        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(ResponseDto.response("잘못된 요청입니다.", errors));
    }

}
