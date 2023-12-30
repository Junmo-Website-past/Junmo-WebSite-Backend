package com.junmo.junmowebsite.domain.board.dto;

import com.junmo.junmowebsite.domain.board.entity.Board;
import lombok.Builder;
import lombok.Getter;

@Getter
public class BoardDto {

    private Long id;
    private String name;
    private String description;

    @Builder
    public BoardDto(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public BoardDto EntityToDto(Board entity) {
        return BoardDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .build();
    }


}
