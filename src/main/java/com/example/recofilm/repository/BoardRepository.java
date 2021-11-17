package com.example.recofilm.repository;

import com.example.recofilm.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    @Modifying
    @Transactional
    @Query(value = "UPDATE Board set view_count = view_count + 1 where num = ?1")
    Integer findbyIdView_count(Long num);
}
;