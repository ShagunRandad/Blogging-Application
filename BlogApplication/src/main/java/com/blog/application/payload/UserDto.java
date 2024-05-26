package com.blog.application.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
	
      private int id;
      
     @NotEmpty
     @Size(min=3,message="username mast be greater than three characters")
      private String name;
      @Email(message="Please Enter a valid Email")
      private String email;
      @NotEmpty(message = "about cannot be empty")
      @Pattern(
          regexp = "^\\s*(\\w+\\s+){9}\\w+\\s*$",
          message = "about must have at least 10 words"
      )
      private String about;
}
