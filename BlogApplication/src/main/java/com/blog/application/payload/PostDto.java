package com.blog.application.payload;

import java.util.Date;

import com.blog.application.entity.Category;
import com.blog.application.entity.User;

import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {
	@NotNull(message = "postTitle Cannot be null")
	private String postTitle;
    @NotNull
    (message = "Please add some content")
	private String content;
	private String imageName;
	private Date addeddate;
	private CategoryDto category;
	private UserDto user;
	
	
	
	
}
