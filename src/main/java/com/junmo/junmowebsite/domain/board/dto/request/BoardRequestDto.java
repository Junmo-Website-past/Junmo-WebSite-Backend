package com.junmo.junmowebsite.domain.board.dto.request;

import com.junmo.junmowebsite.domain.board.entity.Board;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Getter
public class BoardRequestDto {

    @NotBlank(message = "올바른 형태의 게시판 이름이 아닙니다. 한 글자 이상의 문자열이 필요합니다.")
    private String name;
    private String description;

    @Builder
    public BoardRequestDto(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Board DtoToEntity () {
        return Board.builder()
                .name(this.name)
                .description(this.description)
                .build();
    }


}
