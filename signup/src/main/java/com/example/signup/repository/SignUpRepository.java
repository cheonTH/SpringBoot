package com.example.signup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.signup.model.SignUpEntity;

@Repository
public interface SignUpRepository extends JpaRepository<SignUpEntity, String>{

}
