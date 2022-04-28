package com.codewithanurag.blog.service;

import java.util.List;

import com.codewithanurag.blog.payloads.PostDto;
import com.codewithanurag.blog.payloads.PostResponse;

public interface PostService {
	
	//CREATED
	PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);
	
	//UPDTAE
	PostDto updatePost(PostDto postDto, Integer postId);
	
	//DELETE
	void deletePost(Integer postId);
	
	//GETALL
	PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);
	
	//GetSinglePost
	PostDto getPostById(Integer postId);
	
	//get all post by category
	List<PostDto> getAllPostBycategory(Integer categoryId);
	
	//get all post by user
	List<PostDto> getAllPostByUser(Integer userId);
	
	//Search Post
	List<PostDto> searchPosts(String keyord);
	
	

}
