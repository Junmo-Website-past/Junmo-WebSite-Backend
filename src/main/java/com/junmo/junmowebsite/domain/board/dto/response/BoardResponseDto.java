package com.junmo.junmowebsite.domain.board.dto.response;

import com.junmo.junmowebsite.domain.board.entity.Board;
import lombok.Builder;
import lombok.Getter;

@Getter
public class BoardResponseDto {

    private Long id;
    private String name;
    private String description;

    @Builder
    public BoardResponseDto(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public static BoardResponseDto EntityToDto(Board entity) {
        return BoardResponseDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .build();
    }

}
