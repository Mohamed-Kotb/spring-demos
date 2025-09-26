package com.in28Min.restwebservice.restfullwebservice.user;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;

@RestController
public class UsersResource {

	@Autowired
	UserDaoService userDaoService;

	@GetMapping("/users")
	public List<UserJPA> getAllUsers() {
		return userDaoService.findAll();
	}

	@GetMapping("/users/{id}")
	public UserJPA getAllUsers(@PathVariable int id) {
		UserJPA user = userDaoService.findOne(id);
		if (user == null) {
			throw new UserNotFoundException(String.format("no user exist with id %s", id));
		}
		return user;
	}

	@DeleteMapping("/users/{id}")
	public void delete(@PathVariable int id) {
		userDaoService.deleteById(id);
	}

	@PostMapping("/users")
	public ResponseEntity<Object> addUser(@Valid @RequestBody UserJPA user) {
		UserJPA savedUser = userDaoService.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
				.toUri();
		return ResponseEntity.created(location).body(savedUser);
	}
}
