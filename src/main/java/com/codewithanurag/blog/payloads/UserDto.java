package com.codewithanurag.blog.payloads;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
 
	public int id;
	
	//@NotNull
	@NotEmpty
	@Size(min = 4,message = "Name must be minimum 4 characters")
	public String name;
	
	//@javax.validation.constraints.Email
	@Email(message = "Email address is not valid")
	public String email;
	
	//@NotNull
	@NotEmpty
	@Size(min = 3, max = 10,message = "Password must be min 3 chars and max 10 chars ")
	//@Pattern(regexp = "^(.{0,7}|[^0-9]*|[^A-Z]*|[^a-z]*|[a-zA-Z0-9]*)$", message = "Anything with less than eight characters OR "
			//+ "anything with no numbers OR anything with no uppercase OR or anything with no lowercase"
			//+ " OR anything with no special characters")
	public String password;
	
	//@NotNull
	@NotEmpty 
	public String about;
}
