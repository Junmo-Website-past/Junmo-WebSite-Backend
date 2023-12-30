package com.junmo.junmowebsite.domain.board.controller;

import com.junmo.junmowebsite.domain.board.dto.response.BoardResponseDto;
import com.junmo.junmowebsite.domain.board.dto.request.BoardCreateRequestDto;
import com.junmo.junmowebsite.domain.board.service.BoardService;
import com.junmo.junmowebsite.global.util.ResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/boards")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    // C
    @PostMapping
    public ResponseEntity<ResponseDto<BoardResponseDto>> boardAdd(
            @RequestBody @Valid BoardCreateRequestDto requestDto
    ) {

        BoardResponseDto responseDto = boardService.addBoard(requestDto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ResponseDto.response("게시판 생성 완료", responseDto));
    }
}
