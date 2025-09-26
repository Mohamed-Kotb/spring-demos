package com.in28Min.restwebservice.restfullwebservice.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.in28Min.restwebservice.restfullwebservice.user.UserJPA;

public interface UserRepository extends JpaRepository<UserJPA, Integer> {

}
