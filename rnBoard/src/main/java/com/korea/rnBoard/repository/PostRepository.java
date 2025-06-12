package com.korea.rnBoard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.korea.rnBoard.domain.Post;


@Repository
public interface PostRepository extends JpaRepository<Post, Long>{
	
}
