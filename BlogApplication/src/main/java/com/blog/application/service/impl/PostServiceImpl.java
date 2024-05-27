package com.blog.application.service.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.query.Page;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.blog.application.entity.Category;
import com.blog.application.entity.Post;
import com.blog.application.entity.User;
import com.blog.application.exception.NoRecordPresent;
import com.blog.application.payload.PostDto;
import com.blog.application.payload.PostResponceDto;
import com.blog.application.reposistry.CategoryReposistry;
import com.blog.application.reposistry.PostReposistry;
import com.blog.application.reposistry.UserReposistry;
import com.blog.application.service.PostService;
@Service
public class PostServiceImpl implements PostService {
	
	@Autowired
	PostReposistry postReposistry;
	
	@Autowired
	private UserReposistry userReposistry;
	
	@Autowired
	private CategoryReposistry categoryReposistry;
	
	@Autowired
	ModelMapper modelMapper;

	@Override
	public PostDto addNewPost(PostDto postDto,int userId,int categoryId) {
		
		User user=userReposistry.findById(userId).orElseThrow(()-> new NoRecordPresent("No User Present of this id: "+userId));
		Category category=categoryReposistry.findById(categoryId).orElseThrow(()-> new NoRecordPresent("No Category Present of this id: "+categoryId));
		Post post= modelMapper.map(postDto, Post.class);
		post.setImageName("default.png");
		post.setAddeddate(new Date());
		post.setUser(user);
		post.setCategory(category);
	Post newPost=	this.postReposistry.save(post);
		 return this.modelMapper.map(newPost, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, int postId) {
		Post post= this.postReposistry.findById(postId).orElseThrow(()-> 
        new NoRecordPresent("No User Present of this post id: "+postId));
		post.setPostTitle(postDto.getPostTitle());
		post.setContent(postDto.getContent());
		post.setImageName(postDto.getImageName());
	    Post updatedPost=	this.postReposistry.save(post);
		return this.modelMapper.map(updatedPost, PostDto.class);
	}

	@Override
	public Boolean deletePostById(int postId) {
		Post post= this.postReposistry.findById(postId).orElseThrow(()-> 
        new NoRecordPresent("No User Present of this post id: "+postId));
		if(post !=null) {
			this.postReposistry.deleteById(postId);
			return true;
		}
		return false;
	}

	@Override
	public PostResponceDto getAllPost(int pageNumber, int pageSize) {
		Pageable page = PageRequest.of(pageNumber, pageSize);
		org.springframework.data.domain.Page<Post> postPage=this.postReposistry.findAll(page);

		List<PostDto> postDtoList = postPage.getContent().stream()
		        .map(post -> this.modelMapper.map(post, PostDto.class))
		        .toList();

		PostResponceDto postResponseDto = new PostResponceDto();
		postResponseDto.setContent(postDtoList);
		postResponseDto.setPageNumber(postPage.getPageable().getPageNumber());
		postResponseDto.setPageSize(postPage.getPageable().getPageSize());
		postResponseDto.setTotalPages(postPage.getTotalPages());
		postResponseDto.setTotalElements(postPage.getTotalElements());
		postResponseDto.setLastPage(postPage.isLast());
		return postResponseDto;
	}

	@Override
	public PostDto getPostById(int postId) {
		Post post= this.postReposistry.findById(postId).orElseThrow(()-> 
         new NoRecordPresent("No User Present of this post id: "+postId));
		 return this.modelMapper.map(post, PostDto.class);
	}

	@Override
	public List<PostDto> getPostByCategury(int categoryId) {
		Category category=categoryReposistry.findById(categoryId).orElseThrow(()-> 
		 new NoRecordPresent("No Category Present of this id: "+categoryId));
	List<Post> post=this.postReposistry.findByCategory(category);
	return  post.stream().map(s-> this.modelMapper.map(s, PostDto.class)).toList();
		
		
	}

	@Override
	public List<PostDto> getAllPostByUser(int userId) {
		User user=this.userReposistry.findById(userId).orElseThrow(()
				->new NoRecordPresent("No User Present of this id: "+userId));
		List<Post> postList=this.postReposistry.findByUser(user);
		return postList.stream().map(s-> this.modelMapper.map(s, PostDto.class)).toList();
	}

	@Override
	public List<PostDto> getPostByHashTeg(String hashtag) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
