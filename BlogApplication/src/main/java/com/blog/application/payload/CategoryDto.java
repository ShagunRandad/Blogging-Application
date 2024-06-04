package com.blog.application.payload;

import java.io.Serializable;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryDto  implements Serializable{
	private int id;
	   @NotEmpty(message = "category desc cant be empty")
	      @Pattern(
	    		  regexp = "^\\s*(\\w+[\\s,]+){9,}\\w+\\s*$",
	          message = "category desc must have at least 10 words"
	      )
	private String categorydesc;
	   @NotEmpty(message="categoryTittle cant be empty")
	   @Size(min=4, max=10)
	private String categoryTittle;
}
