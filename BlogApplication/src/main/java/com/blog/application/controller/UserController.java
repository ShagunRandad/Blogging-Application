package com.blog.application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blog.application.payload.UserDto;
import com.blog.application.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value="/v1/api/user")
public class UserController {
	
	@Autowired 
	UserService userService;
	
	@PostMapping(value="/create-new-user")
		public UserDto addNewUser(@Valid @RequestBody UserDto user) {
			return userService.createuser(user);
		}
	
	
	@PostMapping(value="/update-use/{id}")
	public UserDto updateUser( @Valid @RequestBody UserDto user,@PathVariable int id) {
		return userService.updateUser(user, id);
	}

	@GetMapping(value="/getUserById/{id}")
	public UserDto getUserById(@PathVariable int id) {
		return userService.getUserBiId(id);
	}
	
	@GetMapping(value="/getAllUsers")
	public List<UserDto> getAllUser(
			@RequestParam(value="pageNumber", defaultValue = "5",required = false) int pageNumber,
			@RequestParam(value="pageSize",defaultValue ="1",required = false) int pageSize
			){
		return userService.getAllUsers(pageNumber,pageSize);
	}
	
	@DeleteMapping(value="/deleteUserById/{id}")
	public Boolean deleteUser(@PathVariable int id) {
		return userService.deleteUserById(id);
	}
	

}
