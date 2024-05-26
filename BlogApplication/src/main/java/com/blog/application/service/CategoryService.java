package com.blog.application.service;

import java.util.List;

import com.blog.application.payload.CategoryDto;

public interface CategoryService {
     
	  CategoryDto createCategory(CategoryDto categoryDto);
	  
	  CategoryDto updateCategory(CategoryDto categoryDto , int categoryId);
	  
	  Boolean deleteCategory(int categoryId);
	  
	  List<CategoryDto> getAllCategorys();
	  
	  CategoryDto getCategoryById( int categoryId);
	
	
}
