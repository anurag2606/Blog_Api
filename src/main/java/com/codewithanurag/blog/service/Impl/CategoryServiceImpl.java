package com.codewithanurag.blog.service.Impl;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codewithanurag.blog.Entity.Category;
import com.codewithanurag.blog.Entity.User;
import com.codewithanurag.blog.exception.ResourceNotFoundException;
import com.codewithanurag.blog.payloads.CategoryDto;
import com.codewithanurag.blog.payloads.UserDto;
import com.codewithanurag.blog.repository.CategoryRepo;
import com.codewithanurag.blog.service.CategoryService;
import com.codewithanurag.blog.service.UserService;

import net.bytebuddy.asm.Advice.This;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		 
	Category cat =	this.modelMapper.map(categoryDto, Category.class);
	Category addCat = this.categoryRepo.save(cat);
		
		return this.modelMapper.map(addCat, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
		Category cat = this.categoryRepo.findById(categoryId)
				.orElseThrow(()-> new ResourceNotFoundException("Category", "Category Id", categoryId));
		
		//update Entities
		cat.setCategoryTitle(categoryDto.getCategoryTitle());
		cat.setCategoryDescription(categoryDto.getCategoryDescription());
		
	     Category updateCat = this.categoryRepo.save(cat);
		
		return this.modelMapper.map(updateCat, CategoryDto.class);
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		Category cat = this.categoryRepo.findById(categoryId)
				.orElseThrow(()-> new ResourceNotFoundException("Category", "Category Id", categoryId));
		this.categoryRepo.delete(cat);
		
	}

	@Override
	public CategoryDto getCategory(Integer categoryId) {
		
		Category cat = this.categoryRepo.findById(categoryId)
				.orElseThrow(()-> new ResourceNotFoundException("Category", "Category Id", categoryId));
		return this.modelMapper.map(cat, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getAllCategory() {
	       List<Category> categories =  this.categoryRepo.findAll();
	      List<CategoryDto> cateDtos = categories.stream().map((cat)->this.modelMapper.map(cat, CategoryDto.class)).collect(Collectors.toList());
		return cateDtos;
	}

	
}
