package com.blog.application.reposistry;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blog.application.entity.Category;
import com.blog.application.entity.Post;
import com.blog.application.entity.User;

@Repository
public interface PostReposistry extends JpaRepository<Post, Integer> {
	
	List<Post> findByUser(User user);
	
	List<Post> findByCategory(Category category);
	
	List<Post> findByPostTitleContaining(String tittle);

}
