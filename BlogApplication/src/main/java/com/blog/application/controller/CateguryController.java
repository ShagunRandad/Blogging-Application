package com.blog.application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.application.payload.CategoryDto;
import com.blog.application.service.impl.CategoryServiceImpl;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping(value="/v1/api/categury")
public class CateguryController {
	
	@Autowired
	CategoryServiceImpl categoryServiceImpl;
	
	@GetMapping(value="/getAllCategury")
	public List<CategoryDto> getAllCategury(){
		return categoryServiceImpl.getAllCategorys();
	}
	
	@PostMapping(value="/create-category")
	public CategoryDto createCategory(@Valid @RequestBody CategoryDto categoryDto) {
		return categoryServiceImpl.createCategory(categoryDto);
	}
	
	
	@PutMapping(value="/updateCategory/{id}")
	public CategoryDto updateCategory(@Valid @RequestBody CategoryDto categoryDto,@PathVariable int id) {
		return categoryServiceImpl.updateCategory(categoryDto, id);
	}
	
	@DeleteMapping(value="/deleteCategory/{id}")
	public Boolean deleteCategory(@PathVariable int id) {
		return categoryServiceImpl.deleteCategory(id);
	}
	
	@GetMapping("/getCategoryById/{id}")
	public CategoryDto getCategoryById(@PathVariable int id) {
		return categoryServiceImpl.getCategoryById(id);
	}
	
	
	
	

}
