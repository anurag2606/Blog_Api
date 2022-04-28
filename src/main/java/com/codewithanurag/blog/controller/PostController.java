package com.codewithanurag.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codewithanurag.blog.config.AppConstants;
import com.codewithanurag.blog.payloads.ApiResponse;
import com.codewithanurag.blog.payloads.PostDto;
import com.codewithanurag.blog.payloads.PostResponse;
import com.codewithanurag.blog.service.PostService;

@RestController
@RequestMapping("/api/")
public class PostController {
	
	//private static final String K = null;
	@Autowired
	private PostService postService;
	
	//POST
	@PostMapping("user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(
			@RequestBody PostDto postDto,
			@PathVariable Integer userId,
			@PathVariable Integer categoryId)
	{
		PostDto createPost = this.postService.createPost(postDto, userId, categoryId);
		return new ResponseEntity<PostDto>(createPost,HttpStatus.CREATED);
		
		
	}
	
	//Get post by user
	@GetMapping("user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Integer userId)
	{
		    List<PostDto> posts = this.postService.getAllPostByUser(userId);
		    return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);
		    
	}
	
	//Get post by category
	@GetMapping("category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable Integer categoryId)
	{
		    List<PostDto> posts = this.postService.getAllPostBycategory(categoryId);
		    return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);
		    
	}

	
	//GET all post
	@GetMapping("/posts")
	//Pagging used by url
	public ResponseEntity<PostResponse> getAllPosts(
			@RequestParam(value="pageNumber", defaultValue=AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
			@RequestParam(value="pageSize", defaultValue=AppConstants.PAGE_SIZE , required = false)Integer pageSize,
			@RequestParam(value="sortBy", defaultValue = AppConstants.SORT_BY, required=false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = AppConstants.SORT_DIR, required = false) String sortDir)
			{
	 PostResponse postResponse =	this.postService.getAllPost(pageNumber, pageSize, sortBy, sortDir);
	 return new ResponseEntity<PostResponse>(postResponse, HttpStatus.OK);
	}
	
	
	//Get post by id
	@GetMapping("/posts/{postId}")
	public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId)
	{
		PostDto postDto = this.postService.getPostById(postId);
		return new ResponseEntity<PostDto>(postDto, HttpStatus.OK);
	}
	
	//Delete post
	@DeleteMapping("/posts/{postId}")
	public ResponseEntity<ApiResponse> deletePostById(@PathVariable Integer postId)
	{
		 this.postService.deletePost(postId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Category is deleted Successfully  !!",true), HttpStatus.OK);
	}
	
	//Update post
	@PutMapping("/posts/{postId}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable Integer postId)
	{
		PostDto updatePost = this.postService.updatePost(postDto, postId);
		return new ResponseEntity<PostDto>(updatePost, HttpStatus.OK);
	}
	
	//Search Post
	@GetMapping("/posts/search/{keywords}")
	public ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable("keywords") String keywords)
	{
	List<PostDto> postDtos = this.postService.searchPosts(keywords);
	return new ResponseEntity<List<PostDto>>(postDtos,HttpStatus.OK);
	}
}
