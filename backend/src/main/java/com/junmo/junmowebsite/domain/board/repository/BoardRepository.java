package com.junmo.junmowebsite.domain.board.repository;

import com.junmo.junmowebsite.domain.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
