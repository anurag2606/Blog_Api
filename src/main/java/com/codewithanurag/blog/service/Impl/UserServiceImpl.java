package com.codewithanurag.blog.service.Impl;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.codewithanurag.blog.exception.*;
import com.codewithanurag.blog.Entity.User;
import com.codewithanurag.blog.payloads.UserDto;
import com.codewithanurag.blog.repository.UserRepo;
import com.codewithanurag.blog.service.UserService;

import net.bytebuddy.asm.Advice.This;


@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public UserDto createUser(UserDto userDto) {
		
		User user = this.dtoToUser(userDto);
	
		//we can use it this line against upper line
		//User user = this.modelMapper.map(userDto, User.class);
		
		User savedUser = this.userRepo.save(user);
		
		return this.userToUserDto(savedUser);
		
		//return this.modelMaper.map(savedUser, UserDto.class)
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
	
		User user = this.userRepo.findById(userId)
				.orElseThrow(()-> new ResourceNotFoundException("User", "Id", userId));
		
		//update Entities
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		
		User updateUser = this.userRepo.save(user);
		UserDto userDto1 = this.userToUserDto(updateUser);
		return userDto1;
	}

	@Override
	public UserDto getUserById(Integer userId) {
		
		User user = this.userRepo.findById(userId)
				.orElseThrow(()-> new ResourceNotFoundException("User", "Id", userId));
		return this.userToUserDto(user);
	}

	@Override
	public List<UserDto> getAllUser() {


		List<User> users = this.userRepo.findAll();
		List<UserDto> userDtos = users.stream().map(user->this.userToUserDto(user)).collect(Collectors.toList());
	
		return userDtos;
	}

	@Override
	public void deleteUser(Integer userId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(()-> new ResourceNotFoundException("User", "Id", userId));
		this.userRepo.delete(user);

	}
	
	
	//Convert UserDto to User
	private User dtoToUser(UserDto userDto) {
		//return user using ModelMapper
		User user = this.modelMapper.map(userDto, User.class);
				
		//use manual data conversion between models 
//		user.setId(userDto.getId());
//		user.setName(userDto.getName());
//		user.setEmail(userDto.getEmail());
//		user.setPassword(userDto.getPassword());
//		user.setAbout(userDto.getAbout());
		
		return user;
		
	}
	
	
	//Convert User to UserDto
		private UserDto userToUserDto(User user) {
			//return userDto using ModelMapper
			UserDto userDto = this.modelMapper.map(user, UserDto.class);
//			userDto.setId(user.getId());
//			userDto.setName(user.getName());
//			userDto.setEmail(user.getEmail());  
//			userDto.setPassword(user.getPassword());
//			userDto.setAbout(user.getAbout());
			
			return userDto;

}
		
		
}
