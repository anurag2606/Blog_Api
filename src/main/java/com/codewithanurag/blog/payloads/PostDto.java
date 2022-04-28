package com.codewithanurag.blog.payloads;

import java.util.Date;

import com.codewithanurag.blog.Entity.Category;
import com.codewithanurag.blog.Entity.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {
	
	private int postId;
	
	private String title;
	
	private String content;
	
	private String imageName;
	
	private Date addedDate;
	
	//Remove recursion
	private CategoryDto category;
	
	//Remove recursion
	private UserDto user;
	
	

}
