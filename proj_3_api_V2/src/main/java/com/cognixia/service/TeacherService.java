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
public class TeacherService {
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
		if (user.getIsTeacher() == false) {
			throw new InvalidException("User is not a teacher");
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
		User user = new User(null, firstname, lastName, true, username, password, null);
		User created = userRepo.save(user);
		return created;
	}
	
	public List<Course> getCourses(String username) throws InvalidException {
		User user = getUser(username);
		if (user.getIsTeacher() == false) {
			throw new InvalidException("User is not a teacher");
		}
		
		return courseRepo.getCoursesTeacher(user.getId());
	}
	
//	public Course getCourse(String username, String courseName) throws InvalidException {
//		Optional<Course> found = Assignment.getCourseTeacher(username, courseName);
//		if(found.isEmpty()) {
//			throw new InvalidException("Course doesn't exist"); 
//		}
//		return found.get();
//	}
	
	public List<Assignment> getStudents(String teacherName, String courseName) throws InvalidException {
//		List<Assignment> found = assignmentRepo.getStudents(getCourse(teacherName, courseName).getId());
		return assignmentRepo.getStudents(getCourse(teacherName, courseName).getId());
	}
	
	public Course getCourse(String teacherName, String courseName) throws InvalidException {
		Optional<Course> found = courseRepo.getCourse(getUser(teacherName).getId(), courseName);
		if (found.isEmpty()) {
			throw new InvalidException("Course doesn't exists");
		} 
		return found.get();
	}
	
	
	public Course createCourse(String teacherName, String courseName) throws InvalidException {
		Optional<Course> found = courseRepo.getCourse(getUser(teacherName).getId(), courseName);
		if (found.isPresent()) {
			throw new InvalidException("Course already exists");
		} 
		Course newCourse = new Course(null, courseName, null, null, null, getUser(teacherName));
		Course added = courseRepo.save(newCourse);
		return added;
		
	}
	
	public Assignment createAssignment(String teacherName, String studentName, String courseName, Double grade, Double weight) throws InvalidException {	
		Course course = getCourse(teacherName, courseName);
	
		User student = getUser(studentName);
		
		Assignment newAssignment = new Assignment(null, grade, weight, null, course, student);
		Assignment added = assignmentRepo.save(newAssignment);
		return added;
	}
	
	public Assignment addStudent(String teacherName, String studentName, String courseName) throws InvalidException {
		return createAssignment(teacherName, studentName, courseName, 0.0, 0.0);
	}
	
	public void removeStudent(String studentName, String courseName) throws InvalidException {
//		System.out.println(getUser(studentName).getId());
//		System.out.println(getCourse(teacherName, courseName).getId());
//		assignmentRepo.removeStudent(getCourse(teacherName, courseName).getId(), getUser(studentName).getId());
		
		List<Assignment> found = assignmentRepo.getAssignments(getUser(studentName).getId(), courseName);
		for (Assignment record : found) {
			assignmentRepo.deleteById(record.getId());
		}
		
	}
}
