package com.blog.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.blog.application.entity.Post;
import com.blog.application.payload.PostDto;

@Service
public interface PostService {
	
	PostDto addNewPost(PostDto postDto,int categoryId,int userId);
	
	PostDto updatePost(PostDto postDto , int postId);
	
	Boolean deletePostById(int postId);
	
	List<PostDto> getAllPost();
	
	PostDto getPostById(int postId);
	
	List<PostDto> getPostByCategury(int categoryId);
	 
    List<PostDto> getAllPostByUser(int userId);
	
	List<PostDto> getPostByHashTeg(String hashtag);

}
