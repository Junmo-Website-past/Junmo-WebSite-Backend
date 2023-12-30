package com.junmo.junmowebsite.domain.board.dto.request;

import com.junmo.junmowebsite.domain.board.entity.Board;
import lombok.Builder;
import lombok.Getter;

@Getter
public class BoardCreateRequestDto {

    private String name;
    private String description;

    @Builder
    public BoardCreateRequestDto(String name, String description) {
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
