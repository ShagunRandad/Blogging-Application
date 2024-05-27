package com.blog.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.blog.application.payload.PostDto;
import com.blog.application.payload.PostResponceDto;

@Service
public interface PostService {
	
	PostDto addNewPost(PostDto postDto,int userId,int categoryId);
	
	PostDto updatePost(PostDto postDto , int postId);
	
	Boolean deletePostById(int postId);
	
	PostResponceDto getAllPost(int pageNumber, int pageSize);
	
	PostDto getPostById(int postId);
	
	List<PostDto> getPostByCategury(int categoryId);
	 
    List<PostDto> getAllPostByUser(int userId);
	
	List<PostDto> getPostByHashTeg(String hashtag);

}
