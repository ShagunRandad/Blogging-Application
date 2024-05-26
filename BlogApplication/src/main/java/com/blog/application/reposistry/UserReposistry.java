package com.blog.application.reposistry;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.application.entity.User;



public interface UserReposistry extends JpaRepository<User, Integer> {

}
