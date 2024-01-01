package com.junmo.junmowebsite.domain.board.exception;

import com.junmo.junmowebsite.global.util.ResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class BoardControllerAdvice {

    @ExceptionHandler(BoardNotFoundException.class)
    public ResponseEntity<ResponseDto> boardNotFoundException(
        BoardNotFoundException e
    ) {
        log.error(e.getMessage(), e);
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(ResponseDto.response(e.getMessage()));
    }


}
