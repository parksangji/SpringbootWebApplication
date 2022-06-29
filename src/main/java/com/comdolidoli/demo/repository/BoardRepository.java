package com.comdolidoli.demo.repository;

import com.comdolidoli.demo.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board,Long> {
}
