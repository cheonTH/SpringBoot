package com.team.team.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.team.team.model.BoardEntity;

public interface BoardRepository extends JpaRepository<BoardEntity, Long>{

}
