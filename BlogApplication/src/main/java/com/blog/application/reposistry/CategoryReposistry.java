package com.blog.application.reposistry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blog.application.entity.Category;

@Repository
public interface CategoryReposistry extends JpaRepository<Category, Integer>{
 
}
