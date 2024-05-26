package com.blog.application.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.application.entity.Category;
import com.blog.application.exception.NoRecordPresent;
import com.blog.application.payload.CategoryDto;
import com.blog.application.reposistry.CategoryReposistry;
import com.blog.application.service.CategoryService;


@Service
public class CategoryServiceImpl implements CategoryService {
	
	
	@Autowired
	CategoryReposistry categoryReposistry;
	
	@Autowired 
	ModelMapper modelMapper;

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		categoryReposistry.save(modelMapper.map(categoryDto, Category.class));
		return categoryDto;
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, int categoryId) {
		Category category=categoryReposistry.findById(categoryId).orElseThrow(()-> new NoRecordPresent("No Category Present by this id"+categoryId));
		if(category != null) {
			categoryReposistry.save(modelMapper.map(categoryDto, Category.class));
		}
		return null;
	}

	@Override
	public Boolean deleteCategory(int categoryId) {
		Category category=categoryReposistry.findById(categoryId).orElseThrow(()-> new NoRecordPresent("No Category Present by this id"+categoryId));
		if(category != null) {
			categoryReposistry.deleteById(categoryId);
			return true;
		}
		return false;
	}

	@Override
	public List<CategoryDto> getAllCategorys() {
	    List<Category> totalCategory=categoryReposistry.findAll();
	    return  totalCategory.stream().map(categury-> modelMapper.map(categury, CategoryDto.class)).toList();
	}

	@Override
	public CategoryDto getCategoryById(int categoryId) {
		Category category=categoryReposistry.findById(categoryId).orElseThrow(()-> new NoRecordPresent("No Category Present by this id"+categoryId));
		return modelMapper.map(category, CategoryDto.class);
	}

}
