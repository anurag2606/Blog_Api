package com.codewithanurag.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codewithanurag.blog.Entity.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
