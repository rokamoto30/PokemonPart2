package com.cognixia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognixia.exception.InvalidException;
import com.cognixia.model.Assignment;
import com.cognixia.model.Course;
import com.cognixia.model.User;
import com.cognixia.service.StudentService;


@RequestMapping("/api")
@RestController
public class StudentController {
	@Autowired
	StudentService service;
	
	@GetMapping("/student/login/{username}/{password}")
	public User login(@PathVariable String username, @PathVariable String password) throws InvalidException {
		return service.login(username, password);
	}
	@GetMapping("/student/getCourses/{username}")
	public List<Course> getCourses(@PathVariable String username) throws InvalidException {
		return service.getCourses(username);
	}
//	@GetMapping("/student/getCourse/{username}/{courseName")
//	public Course getCourse(@PathVariable String username, @PathVariable String courseName) throws InvalidException {
//		return service.getCourse(username, courseName);
//	}
	@PostMapping("/student/create/{username}/{password}/{firstName}/{lastName}")
	public User createStudent(@PathVariable String username, @PathVariable String password, @PathVariable String firstName, @PathVariable String lastName) throws InvalidException {
		return service.createUser(username, password, firstName, lastName);
	}
	@GetMapping("/student/getAssignments/{username}/{courseName}")
	public List<Assignment> getAssignments(@PathVariable String username, @PathVariable String courseName) throws InvalidException {
		return service.getAssignments(username, courseName);
	}
}
