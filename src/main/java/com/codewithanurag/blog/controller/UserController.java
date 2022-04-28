package com.codewithanurag.blog.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.hibernate.ObjectDeletedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codewithanurag.blog.payloads.ApiResponse;
import com.codewithanurag.blog.payloads.UserDto;
import com.codewithanurag.blog.service.UserService;

import net.bytebuddy.asm.Advice.This;
import net.bytebuddy.description.ModifierReviewable.OfAbstraction;


@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private UserService userService;

//	@Autowired
//	private UserServiceImpl userServiceImpl;
	
	//POST Create-user
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto)
	{
		UserDto createUserDto = this.userService.createUser(userDto);
		return new ResponseEntity<>(createUserDto,HttpStatus.CREATED);
			
	}
	
	
	//PUT Update-User
	
	//Path URI Variable
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto,@PathVariable("userId") Integer uId)
	{
		UserDto updatedUserDto=this.userService.updateUser(userDto, uId);
		return new ResponseEntity<>(updatedUserDto,HttpStatus.OK);
	}
	
	
	
	//DELETE Delete-User
	@DeleteMapping("/{userId}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer userId)
	{
		//void return
		this.userService.deleteUser(userId);
		
		 //return manually message
	    //return new ResponseEntity<>(Map.of("message", "User deleted successfully"),HttpStatus.OK);  
		
		return new ResponseEntity<ApiResponse>(new ApiResponse("User deleted Successfully", true),HttpStatus.OK); 
	}
	
	
	//GET Get-User
	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getAllUsers(){
		return new ResponseEntity<>(this.userService.getAllUser(),HttpStatus.OK);
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getSingleUser(@PathVariable Integer userId){
		return new ResponseEntity<>(this.userService.getUserById(userId),HttpStatus.OK);
	}
	
	
}
