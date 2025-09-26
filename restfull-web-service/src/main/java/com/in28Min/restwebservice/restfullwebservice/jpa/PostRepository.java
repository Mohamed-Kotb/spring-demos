package com.in28Min.restwebservice.restfullwebservice.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.in28Min.restwebservice.restfullwebservice.user.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {

}
