package com.junmo.junmowebsite.domain.board.entity;

import com.junmo.junmowebsite.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public Board(Long id, String name, String description, Member member) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.member = member;
    }
}
