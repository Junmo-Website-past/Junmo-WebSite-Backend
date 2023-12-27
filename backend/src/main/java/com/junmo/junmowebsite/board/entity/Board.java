package com.junmo.junmowebsite.board.entity;

import com.junmo.junmowebsite.member.entity.Member;
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
