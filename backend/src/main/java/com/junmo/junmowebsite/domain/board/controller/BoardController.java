package com.junmo.junmowebsite.domain.board.controller;

import com.junmo.junmowebsite.domain.board.dto.response.BoardResponseDto;
import com.junmo.junmowebsite.domain.board.dto.request.BoardRequestDto;
import com.junmo.junmowebsite.domain.board.service.BoardService;
import com.junmo.junmowebsite.global.util.ResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/boards")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @PostMapping
    public ResponseEntity<ResponseDto<BoardResponseDto>> boardAdd(
            @RequestBody @Valid BoardRequestDto requestDto
    ) {

        BoardResponseDto responseDto = boardService.addBoard(requestDto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ResponseDto.response("게시판 생성 완료", responseDto));
    }

    @GetMapping("/{boardId}")
    public ResponseEntity<ResponseDto<BoardResponseDto>> boardDetails(
            @PathVariable Long boardId
    ) {
        BoardResponseDto response = boardService.findBoard(boardId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResponseDto.response("게시판 조회 완료", response));
    }

    @PatchMapping("/{boardId}")
    public ResponseEntity<ResponseDto<BoardResponseDto>> boardModify(
        @PathVariable Long boardId,
        @RequestBody @Valid BoardRequestDto requestDto
    ) {
        BoardResponseDto responseDto = boardService.modifyBoard(boardId, requestDto);

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(ResponseDto.response("게시판 수정 완료", responseDto));
    }

    @DeleteMapping("/{boardId}")
    public ResponseEntity<ResponseDto<Object>> boardRemove(
        @PathVariable Long boardId
    ) {

        boardService.removeBoard(boardId);

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(ResponseDto.response("게시판 삭제 완료"));
    }

















}
