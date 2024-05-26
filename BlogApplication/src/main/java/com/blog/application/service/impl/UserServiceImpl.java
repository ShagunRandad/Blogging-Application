package com.blog.application.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.application.entity.User;
import com.blog.application.exception.NoRecordPresent;
import com.blog.application.payload.UserDto;
import com.blog.application.reposistry.UserReposistry;
import com.blog.application.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	ModelMapper modalMapper;
	
	@Autowired
	UserReposistry userReposistry;
	
	
	@Override
	public UserDto createuser(UserDto userDto) {
	    User user= modalMapper.map(userDto, User.class);
	    userReposistry.save(user);
		return userDto;
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		User user = userReposistry.findById(userId)
			    .orElseThrow(() -> new NoRecordPresent("No Record is present for this id: " + userId));

		if(user!= null) {
			userReposistry.save(modalMapper.map(userDto, User.class));
			return userDto;
		}
		return null;
	}

	@Override
	public UserDto getUserBiId(Integer userId) {
		User user = userReposistry.findById(userId)
			    .orElseThrow(() -> new NoRecordPresent("No Record is present for this id: " + userId));
		return modalMapper.map(user, UserDto.class);
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> tempUserList=userReposistry.findAll();
		return tempUserList.stream().map(user-> modalMapper.map(user, UserDto.class)).toList();
	}

	@Override
	public Boolean deleteUserById(Integer userID) {
		User user = userReposistry.findById(userID)
			    .orElseThrow(() -> new NoRecordPresent("No Record is present for this id: " + userID));
		if(user != null){
			userReposistry.deleteById(userID);
			return true;
		}
		return false;
	}

}
