package com.junmo.junmowebsite.domain.board.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.junmo.junmowebsite.domain.board.dto.request.BoardRequestDto;
import com.junmo.junmowebsite.domain.board.entity.Board;
import com.junmo.junmowebsite.domain.board.exception.BoardNotFoundException;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @DisplayName("Board 저장 성공")
    @Test
    void givenBoard_whenSaveBoard_thenBoardSaved() throws Exception{
        // given
        BoardRequestDto requestDto = BoardRequestDto.builder()
            .name("test_name01")
            .description("test_description01")
            .build();

        Board requestBoard = requestDto.DtoToEntity();

        // when
        Board savedBoard = boardRepository.save(requestBoard);

        // then
        assertThat(savedBoard)
            .usingRecursiveComparison()
            .isEqualTo(requestBoard)
            .ignoringFields("id");
    }

    @DisplayName("Board 조회 성공")
    @Test
    void givenBoardId_whenFindBoard_thenBoardFound() throws Exception{
        // given
        BoardRequestDto requestDto = BoardRequestDto.builder()
            .name("test_name01")
            .description("test_description01")
            .build();

        Board requestBoard = requestDto.DtoToEntity();

        Board savedBoard = boardRepository.save(requestBoard);

        // when
        Board foundBoard = boardRepository.findById(savedBoard.getId())
                                            .orElseThrow(BoardNotFoundException::new);

        // then
        assertThat(foundBoard).isNotNull();
        assertThat(foundBoard)
            .usingRecursiveComparison()
            .isEqualTo(savedBoard);
    }

    @DisplayName("Board 수정 성공")
    @Test
    void givenIdAndBoard_whenUpdateBoard_thenBoardUpdated() throws Exception{
        // given
        BoardRequestDto requestDto = BoardRequestDto.builder()
            .name("test_name01")
            .description("test_description01")
            .build();

        BoardRequestDto updateDto = BoardRequestDto.builder()
            .name("updated_name")
            .description("updated_description")
            .build();

        Board requestBoard = requestDto.DtoToEntity();

        Board savedBoard = boardRepository.save(requestBoard);

        // when
        savedBoard.update(updateDto);

        Board updatedBoard = boardRepository.save(savedBoard);

        // then
        assertThat(updatedBoard.getName()).isEqualTo(updateDto.getName());
        assertThat(updatedBoard.getDescription()).isEqualTo(updateDto.getDescription());
    }

    @DisplayName("Board 삭제 확인")
    @Test
    void givenBoard_whenDeleteById_thenBoardDeleted() throws Exception {
        // given
        BoardRequestDto requestDto = BoardRequestDto.builder()
            .name("test_name01")
            .description("test_description01")
            .build();

        Board requestBoard = requestDto.DtoToEntity();

        Board savedBoard = boardRepository.save(requestBoard);

        // when
        boardRepository.deleteById(savedBoard.getId());

        // then
        Optional<Board> deletedBoard = boardRepository.findById(savedBoard.getId());
        assertThat(deletedBoard).isEmpty();
    }


}
