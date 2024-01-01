package com.junmo.junmowebsite.domain.board.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.junmo.junmowebsite.domain.board.dto.request.BoardRequestDto;
import com.junmo.junmowebsite.domain.board.dto.response.BoardResponseDto;
import com.junmo.junmowebsite.domain.board.repository.BoardRepository;
import com.junmo.junmowebsite.domain.board.service.BoardService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest(BoardController.class)
class BoardControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private BoardService boardService;

    @MockBean
    private BoardRepository boardRepository;

    @DisplayName("boardAdd()를 통해 게시판(Board)을 추가할 수 있다.")
    @Test
    void givenBoard_whenAddBoard_thenBoardAdd() throws Exception {
        // given
        BoardRequestDto requestDto = BoardRequestDto.builder()
                                        .name("test_name01")
                                        .description("test_description01")
                                        .build();

        BoardResponseDto responseDto = BoardResponseDto.builder()
                                            .id(1L)
                                            .name("test_name01")
                                            .description("test_description01")
                                            .build();

        given(boardService.addBoard(any(BoardRequestDto.class))).willReturn(responseDto);

        // when
        String requestBody = mapper.writeValueAsString(requestDto);

        ResultActions resultActions = mvc.perform(post("/api/v1/boards")
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .content(requestBody))
                                        .andDo(print());

        // then
        resultActions
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.message").value("게시판 생성 완료"))
            .andExpect(jsonPath("$.data.name").value("test_name01"))
            .andExpect(jsonPath("$.data.description").value("test_description01"));

        verify(boardService).addBoard(any(BoardRequestDto.class));

    }

    @DisplayName("boardDetails()를 통해 게시판(Board)을 조회할 수 있다.")
    @Test
    void givenBoardId_whenFindBoard_thenBoardDetails() throws Exception{

        // given
        Long requestBoardId = 1L;

        BoardResponseDto responseDto = BoardResponseDto.builder()
                                        .id(1L)
                                        .name("test_name01")
                                        .description("test_description01")
                                        .build();

        given(boardService.findBoard(requestBoardId)).willReturn(responseDto);

        // when
        ResultActions resultActions = mvc.perform(MockMvcRequestBuilders.get("/api/v1/boards/{boardId}", requestBoardId)
                                        .contentType(MediaType.APPLICATION_JSON))
                                        .andDo(print());

        // then
        resultActions
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.message").value("게시판 조회 완료"))
            .andExpect(jsonPath("$.data.id").value(1L))
            .andExpect(jsonPath("$.data.name").value("test_name01"))
            .andExpect(jsonPath("$.data.description").value("test_description01"));

        verify(boardService).findBoard(requestBoardId);


    }

    @DisplayName("boardModify()를 통해 게시판(Board)을 수정할 수 있다.")
    @Test
    void givenBoard_whenModifyBoard_thenBoardModify() throws Exception{

        // given
        Long requestBoardId = 1L;

        BoardRequestDto requestDto = BoardRequestDto.builder()
            .name("test_name01")
            .description("test_description01")
            .build();

        BoardResponseDto responseDto = BoardResponseDto.builder()
            .id(1L)
            .name("test_name01")
            .description("test_description01")
            .build();

        given(boardService.modifyBoard(anyLong(), any(BoardRequestDto.class))).willReturn(responseDto);

        // when
        String requestBody = mapper.writeValueAsString(requestDto);

        ResultActions resultActions = mvc.perform(patch("/api/v1/boards/{boardId}", requestBoardId)
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .content(requestBody))
                                        .andDo(print());

        // then
        resultActions
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.message").value("게시판 수정 완료"))
            .andExpect(jsonPath("$.data.id").value(1L))
            .andExpect(jsonPath("$.data.name").value("test_name01"))
            .andExpect(jsonPath("$.data.description").value("test_description01"));

        verify(boardService).modifyBoard(anyLong(), any(BoardRequestDto.class));

    }

    @DisplayName("boardRemove()를 통해 게시판(Board)을 제거할 수 있다.")
    @Test
    void givenBoardId_whenRemoveBoard_thenBoardRemove() throws Exception{

        // given
        Long requestBoardId = 1L;

        doNothing().when(boardService).removeBoard(requestBoardId);

        // when
        ResultActions resultActions = mvc.perform(delete("/api/v1/boards/{boardId}", requestBoardId)
                                        .contentType(MediaType.APPLICATION_JSON))
                                        .andDo(print());

        // then
        resultActions
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.message").value("게시판 삭제 완료"));

        verify(boardService).removeBoard(requestBoardId);

    }



}