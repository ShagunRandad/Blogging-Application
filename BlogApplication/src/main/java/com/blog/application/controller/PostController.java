package com.blog.application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blog.application.config.AppConstant;
import com.blog.application.payload.PostDto;
import com.blog.application.payload.PostResponceDto;
import com.blog.application.service.PostService;

@RestController
@RequestMapping(value="/v1/api/post")
public class PostController {
	
	@Autowired
	PostService postService;


	
	@PostMapping(value = "/save-post/user/{userId}/category/{CategoryId}")
	public ResponseEntity<PostDto> createNewPost(
			@RequestBody PostDto postDto, 
			@PathVariable("userId") int userId,
			@PathVariable("CategoryId") int categoryId) {
		PostDto post = postService.addNewPost(postDto, userId, categoryId);
		return new ResponseEntity<PostDto>(post, HttpStatus.CREATED);
	}
	
	
	@GetMapping(value="/getpostsByCategoryId/{id}")
	public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable int id){
		return new ResponseEntity<List<PostDto>>(this.postService.getPostByCategury(id),HttpStatus.OK);
	}
	
	
	@GetMapping(value="/getPostByUserId/{id}")
	public ResponseEntity<List<PostDto>> getPostByUserId(@PathVariable int id){
		return new ResponseEntity<List<PostDto>>(this.postService.getAllPostByUser(id),HttpStatus.OK);
	}
	
	@GetMapping(value="/getPostById/{id}")
	public ResponseEntity<PostDto> getPostById(@PathVariable int id){
		return new ResponseEntity<PostDto>(this.postService.getPostById(id),HttpStatus.OK);
	}
	
	@DeleteMapping(value="/deletePostById/{id}")
	public ResponseEntity<Boolean> deletePostById(@PathVariable int id){
		return new ResponseEntity<Boolean>(this.postService.deletePostById(id),HttpStatus.OK);
	}
	
	@GetMapping("/getAllPosts")
	public ResponseEntity<PostResponceDto> getAllPosts(
	        @RequestParam(value = "pageNumber", defaultValue =AppConstant.DEFAULT_PAGE_NUMBER, required = false) int pageNumber,
	        @RequestParam(value = "pageSize", defaultValue = AppConstant.DEFAULT_PAGE_SIZE, required = false) int pageSize,
	        @RequestParam(value = "sortBy", defaultValue = AppConstant.DEFAULT_SORT_BY, required = false) String sortBy,
	        @RequestParam(value = "sortorder", defaultValue = AppConstant.DEFAULT_SORTINF_ORDER, required = false) String sortingOrder
			) {

	    return new ResponseEntity<>(this.postService.getAllPost(pageNumber, pageSize,sortBy,sortingOrder), HttpStatus.OK);
	}

	
	@PutMapping(value="/updatePost/{id}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postdto,@PathVariable int id){
		return new ResponseEntity<PostDto>(this.postService.updatePost(postdto, id),HttpStatus.OK);
	}
	
	@PostMapping(value="/search/hashtag/{hashtag}")
	public ResponseEntity<List<PostDto>> searchByHashtag(@PathVariable String hashtag){
		return new ResponseEntity<List<PostDto>>(this.postService.getPostByHashTeg(hashtag),HttpStatus.OK);
	}
	
	
	
	
}
