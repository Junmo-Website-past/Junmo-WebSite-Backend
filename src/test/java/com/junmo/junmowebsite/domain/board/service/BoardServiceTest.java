package com.junmo.junmowebsite.domain.board.service;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.junmo.junmowebsite.domain.board.dto.request.BoardRequestDto;
import com.junmo.junmowebsite.domain.board.dto.response.BoardResponseDto;
import com.junmo.junmowebsite.domain.board.entity.Board;
import com.junmo.junmowebsite.domain.board.repository.BoardRepository;
import java.util.Objects;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(MockitoExtension.class)
class BoardServiceTest {

    @InjectMocks
    private BoardService boardService;

    @Mock
    private BoardRepository boardRepository;


    @DisplayName("addBoard()를 통해 게시판(Board)을 추가할 수 있다.")
    @Test
    void givenBoard_whenAddBoard_thenBoardSaved() throws Exception{

        // given
        BoardRequestDto requestDto = BoardRequestDto.builder()
            .name("test_name01")
            .description("test_description01")
            .build();

        Board savedBoard = Board.builder()
            .id(1L)
            .name("test_name01")
            .description("test_description01")
            .build();

        BoardResponseDto responseDto = BoardResponseDto.EntityToDto(savedBoard);

        given(boardRepository.save(any(Board.class))).willReturn(savedBoard);

        // when
        BoardResponseDto result = boardService.addBoard(requestDto);

        // then
        assertThat(result).isNotNull();
        assertThat(result).usingRecursiveComparison().isEqualTo(responseDto);
    }

    @DisplayName("findBoard()를 통해 게시판(Board)을 조회할 수 있다.")
    @Test
    void givenBoardId_whenFindBoard_thenFindBoard() throws Exception{

        // given
        Long requestBoardId = 1L;

        Board findBoard = Board.builder()
            .id(1L)
            .name("test_name01")
            .description("test_description01")
            .build();

        BoardResponseDto responseDto = BoardResponseDto.EntityToDto(findBoard);

        given(boardRepository.findById(requestBoardId)).willReturn(Optional.of(findBoard));

        // when
        BoardResponseDto result = boardService.findBoard(requestBoardId);

        // then
        assertThat(result).isNotNull();
        assertThat(result).usingRecursiveComparison().isEqualTo(responseDto);
    }

    @DisplayName("modifyBoard()를 통해 게시판(Board)을 수정할 수 있다.")
    @Test
    void givenBoard_whenModifyBoard_thenBoardModified() throws Exception{

        // given
        Long requestBoardId = 1L;

        BoardRequestDto requestDto = BoardRequestDto.builder()
            .name("test_name01")
            .description("test_description01")
            .build();

        Board findBoard = Board.builder()
            .id(1L)
            .name("test_name01")
            .description("test_description01")
            .build();

        Board modifiedBoard = Board.builder()
            .id(1L)
            .name("test_name01")
            .description("test_description01")
            .build();

        BoardResponseDto responseDto = BoardResponseDto.EntityToDto(modifiedBoard);

        given(boardRepository.findById(requestBoardId)).willReturn(Optional.of(findBoard));

        // when
        BoardResponseDto result = boardService.modifyBoard(requestBoardId, requestDto);

        // then
        assertThat(responseDto.getId()).isEqualTo(requestBoardId);

        assertThat(result).isNotNull();
        assertThat(result).usingRecursiveComparison().isEqualTo(responseDto);
    }


    @DisplayName("removeBoard()를 통해 게시판(Board)을 삭제할 수 있다.")
    @Test
    void givenBoardId_whenRemoveBoard_thenBoardDeleted() throws Exception {

        // given
        Long requestBoardId = 1L;

        Board findBoard = Board.builder()
            .id(1L)
            .name("test_name01")
            .description("test_description01")
            .build();

        given(boardRepository.findById(requestBoardId)).willReturn(Optional.ofNullable(findBoard));

        // when
        boardService.removeBoard(requestBoardId);

        // then
        verify(boardRepository, times(1)).delete(Objects.requireNonNull(findBoard));

    }





}