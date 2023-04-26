package com.cognixia.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cognixia.model.Course;

@Repository
public interface CourseRepo extends JpaRepository<Course, Integer> {
	
//	public Optional<Course> getCourse(String username, String courseName);

	@Query(value ="SELECT c.* FROM course c WHERE c.teacher_id = ?1 AND c.course_name = ?2", nativeQuery = true)
	public Optional<Course> getCourse(Integer id, String courseName);
	
	@Query(value ="SELECT c.* FROM course c WHERE c.teacher_id = ?1 GROUP BY c.id", nativeQuery = true)
	public List<Course> getCoursesTeacher(Integer id);
	
	@Query(value ="SELECT c.* FROM assignment a LEFT JOIN course c ON a.course_id = c.id WHERE a.student_id =?1 GROUP BY c.id", nativeQuery = true)
	public List<Course> getCoursesStudent(Integer id);
	
	
}
