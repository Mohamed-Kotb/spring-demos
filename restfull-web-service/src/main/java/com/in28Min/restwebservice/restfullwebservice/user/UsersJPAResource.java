package com.in28Min.restwebservice.restfullwebservice.user;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.in28Min.restwebservice.restfullwebservice.jpa.UserRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/jpa")
public class UsersJPAResource {

	@Autowired
	UserRepository userRepository;

	@GetMapping("/users")
	public List<UserJPA> getAllUsers() {
		return userRepository.findAll();
	}

	@GetMapping("/users/{id}")
	public UserJPA getAllUsers(@PathVariable int id) {
		Optional<UserJPA> user = userRepository.findById(id);
		if (user.isEmpty()) {
			throw new UserNotFoundException(String.format("no user exist with id %s", id));
		}
		return user.get();
	}

	@DeleteMapping("/users/{id}")
	public void delete(@PathVariable int id) {
		userRepository.deleteById(id);
	}

	@PostMapping("/users")
	public ResponseEntity<Object> addUser(@Valid @RequestBody UserJPA user) {
		UserJPA savedUser = userRepository.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
				.toUri();
		return ResponseEntity.created(location).body(savedUser);
	}
}
