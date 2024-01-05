package com.junmo.junmowebsite.domain.board.entity;

import com.junmo.junmowebsite.domain.member.entity.Member;
import jakarta.persistence.*;

@Entity
public class Comment {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

}
