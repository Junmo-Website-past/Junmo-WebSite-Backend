package com.junmo.junmowebsite.domain.board.entity;

import com.junmo.junmowebsite.domain.member.entity.Member;
import jakarta.persistence.*;

@Entity
public class Board {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;




}
