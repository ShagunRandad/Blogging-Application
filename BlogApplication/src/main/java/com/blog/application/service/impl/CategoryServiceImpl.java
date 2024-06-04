package com.blog.application.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.blog.application.entity.Category;
import com.blog.application.exception.NoRecordPresent;
import com.blog.application.payload.CategoryDto;
import com.blog.application.reposistry.CategoryReposistry;
import com.blog.application.service.CategoryService;


@Service
public class CategoryServiceImpl implements CategoryService {
	
	Logger log = LoggerFactory.getLogger(CategoryServiceImpl.class);
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
	@CachePut(cacheNames = "category",key="#categoryId")
	public CategoryDto updateCategory(CategoryDto categoryDto, int categoryId) {
		log.info("Method has Been Called");
		Category category=categoryReposistry.findById(categoryId).orElseThrow(()-> new NoRecordPresent("No Category Present by this id"+categoryId));
		if(category != null) {
			categoryReposistry.save(modelMapper.map(categoryDto, Category.class));
		}
		return null;
	}

	@Override
	@CacheEvict(cacheNames = "category",key = "#categoryId",allEntries = true)
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
	@Cacheable(cacheNames = "category",key="#categoryId")
	public CategoryDto getCategoryById(int categoryId) {
		log.info("Method has Been Called");
		Category category=categoryReposistry.findById(categoryId).orElseThrow(()-> new NoRecordPresent("No Category Present by this id"+categoryId));
		return modelMapper.map(category, CategoryDto.class);
	}

}
