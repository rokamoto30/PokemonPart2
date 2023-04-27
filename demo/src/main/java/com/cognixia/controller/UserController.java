package com.cognixia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognixia.exception.InvalidException;
import com.cognixia.exception.InvalidLoginException;
import com.cognixia.model.User;
import com.cognixia.service.UserService;


@RequestMapping("/api")
@RestController
public class UserController {
	@Autowired
	UserService service;
	
	@GetMapping("/user/login/{username}/{password}")
	public User login(@PathVariable String username, @PathVariable String password) throws InvalidException, InvalidLoginException {
		return service.login(username, password);
	}
	
	@PostMapping("/user/create/{username}/{password}")
	public User createUser(@PathVariable String username, @PathVariable String password) throws InvalidException {
		return service.createUser(username, password);
	}
	
	
}
