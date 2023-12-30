package com.junmo.junmowebsite.domain.board.service;

import com.junmo.junmowebsite.domain.board.dto.response.BoardResponseDto;
import com.junmo.junmowebsite.domain.board.dto.request.BoardCreateRequestDto;
import com.junmo.junmowebsite.domain.board.entity.Board;
import com.junmo.junmowebsite.domain.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public BoardResponseDto addBoard(BoardCreateRequestDto requestDto) {

        log.info(requestDto.toString());
        System.out.println(requestDto.getName());

        Board board = requestDto.DtoToEntity();

        Board savedBoard = boardRepository.save(board);

        BoardResponseDto responseDto = BoardResponseDto.EntityToDto(savedBoard);

        return responseDto;

    }
}
