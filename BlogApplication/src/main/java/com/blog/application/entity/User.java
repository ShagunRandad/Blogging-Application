package com.blog.application.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="user_master")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
      private int id;
	@Column(name="user_name")
      private String name;
	@Column(name="user_email")
      private String email;
      private String about;
}
