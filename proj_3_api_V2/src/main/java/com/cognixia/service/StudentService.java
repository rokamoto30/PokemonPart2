package com.cognixia.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognixia.exception.InvalidException;
import com.cognixia.model.Assignment;
import com.cognixia.model.Course;
import com.cognixia.model.User;
import com.cognixia.repo.AssignmentRepo;
import com.cognixia.repo.CourseRepo;
import com.cognixia.repo.UserRepo;


@Service
public class StudentService {	
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	CourseRepo courseRepo;
	
	@Autowired
	AssignmentRepo assignmentRepo;
	
	public User getUser(String username) throws InvalidException {
		Optional<User> found = userRepo.findByUsername(username);
		if (found.isEmpty()) {
			throw new InvalidException("User doesn't exist"); 
		}
		return found.get();
	}
	
	public User login(String username, String password) throws InvalidException {
		User user = getUser(username);
		if (user.getIsTeacher()) {
			throw new InvalidException("User is not a student");
		}
		if (user.getPassword().equals(password)) {
			return user;
		} else {
			throw new InvalidException("Password doesn't exist");
		}
	}
	
	public User createUser(String username, String password, String firstname, String lastName) throws InvalidException {
		Optional<User> found = userRepo.findByUsername(username);
		if (found.isPresent()) {
			throw new InvalidException("User already exist"); 
		}
		User user = new User(null, firstname, lastName, false, username, password, null);
		User created = userRepo.save(user);
		return created;
	}
	
	public List<Course> getCourses(String username) throws InvalidException {
		User user = getUser(username);
		if (user.getIsTeacher() == true) {
			throw new InvalidException("User is not a student");
		}
		return courseRepo.getCoursesStudent(user.getId());
	}
	
//	public Course getCourse(String username, String courseName) {
//		Optional<Course> found = courseRepo.getCourseStudent(username);
//		if(found.isEmpty()) {
//			throw new InvalidException("Course doesn't exist"); 
//		}
//		return found.get();
//	}
	
	public List<Assignment> getAssignments(String username, String courseName) throws InvalidException {
		return assignmentRepo.getAssignments(getUser(username).getId(), courseName);
	}
}
