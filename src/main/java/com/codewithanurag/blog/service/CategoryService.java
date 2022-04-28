package com.codewithanurag.blog.service;

import java.util.List;

import com.codewithanurag.blog.payloads.CategoryDto;

public interface CategoryService {

	//CREATTE
	CategoryDto createCategory(CategoryDto categoryDto);
	
	//UPDATE
	CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);

	//DELETE
	void deleteCategory(Integer categoryId);

	//GET
	
	CategoryDto getCategory(Integer categoryId);

	//GETALL
	List<CategoryDto> getAllCategory();

}
