package com.cognixia.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/api")
@RestController
public class UserController {
	@GetMapping("/user/login/{username}/{password}")
	public User login(@PathVariable String username, @PathVariable String password) throws InvalidException {
		return service.login(username, password);
	}
	
	@PostMapping("/user/create/{username}/{password}")
	public User createUser(@PathVariable String username, @PathVariable String password) throws InvalidException {
		return service.createUser(username, password, firstName, lastName);
	}
	
	
}
