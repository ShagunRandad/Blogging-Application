package com.blog.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.blog.application.payload.UserDto;


@Service
public interface UserService {

	UserDto createuser(UserDto uSserDto);

	UserDto updateUser(UserDto userDto, Integer userId);

	UserDto getUserBiId(Integer userId);

	List<UserDto> getAllUsers();

	Boolean deleteUserById(Integer userID);



}
