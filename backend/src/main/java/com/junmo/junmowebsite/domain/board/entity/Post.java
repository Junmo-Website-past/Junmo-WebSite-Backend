package com.junmo.junmowebsite.domain.board.entity;

import com.junmo.junmowebsite.domain.member.entity.Member;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Post {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    @OneToMany(mappedBy = "post")
    private List<Comment> comments;

    @OneToMany(mappedBy = "post")
    private List<Hashtag> hashtags;

}