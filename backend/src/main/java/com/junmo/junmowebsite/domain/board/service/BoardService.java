package com.junmo.junmowebsite.domain.board.service;

import com.junmo.junmowebsite.domain.board.dto.response.BoardResponseDto;
import com.junmo.junmowebsite.domain.board.dto.request.BoardRequestDto;
import com.junmo.junmowebsite.domain.board.entity.Board;
import com.junmo.junmowebsite.domain.board.exception.BoardNotFoundException;
import com.junmo.junmowebsite.domain.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public BoardResponseDto addBoard(BoardRequestDto requestDto) {

        Board board = requestDto.DtoToEntity();

        Board savedBoard = boardRepository.save(board);

        BoardResponseDto responseDto = BoardResponseDto.EntityToDto(savedBoard);

        return responseDto;

    }

    @Transactional(readOnly = true)
    public BoardResponseDto findBoard(Long boardId) {

        Board board = findBoardById(boardId);

        BoardResponseDto responseDto = BoardResponseDto.EntityToDto(board);

        return responseDto;

    }

    @Transactional
    public BoardResponseDto modifyBoard(Long boardId, BoardRequestDto requestDto) {

        Board board = findBoardById(boardId);

        board.update(requestDto);

        BoardResponseDto responseDto = BoardResponseDto.EntityToDto(board);

        return responseDto;
    }

    public void removeBoard(Long boardId) {

        Board board = findBoardById(boardId);

        boardRepository.delete(board);

    }

    private Board findBoardById(Long boardId) {
        Board findBoard = boardRepository.findById(boardId)
            .orElseThrow(BoardNotFoundException::new);
        return findBoard;
    }

}
