package com.codewithanurag.blog.repository;

import org.springframework.data.jpa.repository.JpaContext;
import org.springframework.data.jpa.repository.JpaRepository;

import com.codewithanurag.blog.Entity.User;

public interface UserRepo extends JpaRepository<User, Integer> {
	

}
