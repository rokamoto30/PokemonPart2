package com.cognixia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognixia.exception.InvalidException;
import com.cognixia.model.Assignment;
import com.cognixia.model.Course;
import com.cognixia.model.User;
import com.cognixia.service.TeacherService;

@RequestMapping("/api")
@RestController
public class TeacherController {
	@Autowired
	TeacherService service;
	
	
	@GetMapping("/teacher/login/{username}/{password}")
	public User login(@PathVariable String username, @PathVariable String password) throws InvalidException {
		return service.login(username, password);
	}
	
	@GetMapping("/teacher/getCourses/{username}")
	public List<Course> getCourses(@PathVariable String username) throws InvalidException {
		return service.getCourses(username);
	}
	
//	@GetMapping("/teacher/getCourse/{username}/{courseName}")
//	public Course getCourse(@PathVariable String username, @PathVariable String courseName) throws InvalidException {
//		return service.getCourse(username, courseName);
//	}
	
	@PostMapping("/teacher/create/{username}/{password}/{firstName}/{lastName}")
	public User createTeacher(@PathVariable String username, @PathVariable String password, @PathVariable String firstName, @PathVariable String lastName) throws InvalidException {
		return service.createUser(username, password, firstName, lastName);
	}
	
	
	@GetMapping("/teacher/getStudents/{username}/{courseName}")
	public List<Assignment> getStudents(@PathVariable String username, @PathVariable String courseName) throws InvalidException {
		return service.getStudents(username, courseName);
	}
	
	
	
	@PostMapping("/teacher/createCourse/{username}/{courseName}")
	public Course createCourse(@PathVariable String username, @PathVariable String courseName) throws InvalidException {
		return service.createCourse(username, courseName);
	}
	
	@PostMapping("/teacher/createAssignnment/{teacherName}/{studentName}/{courseName}/{grade}/{weight}")
	public Assignment createAssignment(@PathVariable String teacherName, @PathVariable String studentName, @PathVariable String courseName, @PathVariable Double grade, @PathVariable Double weight) throws InvalidException {
		return service.createAssignment(teacherName, studentName, courseName, grade, weight);
	}
	
	@PostMapping("/teacher/addStudent/{teacherName}/{studentName}/{courseName}")
	public Assignment addStudent(@PathVariable String teacherName, @PathVariable String studentName, @PathVariable String courseName) throws InvalidException {
		return service.addStudent(teacherName, studentName, courseName);
	}
	
	@DeleteMapping("/teacher/deleteStudent/{studentName}/{courseName}")
	public void removeStudent(@PathVariable String studentName, @PathVariable String courseName) throws InvalidException {
		service.removeStudent(studentName, courseName);
	}
	
}
