package com.team.team.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.team.team.model.BoardImageEntity;

import jakarta.transaction.Transactional;

@Repository
public interface BoardImageRepository extends JpaRepository<BoardImageEntity, Long> {
    List<BoardImageEntity> findByBoardId(Long boardId);
    
    @Transactional
    void deleteByBoardId(Long boardId);
}