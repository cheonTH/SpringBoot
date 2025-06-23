package com.team.team.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.team.team.model.CommentEntity;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
    List<CommentEntity> findByBoardIdOrderByIdDesc(Long boardId);
}