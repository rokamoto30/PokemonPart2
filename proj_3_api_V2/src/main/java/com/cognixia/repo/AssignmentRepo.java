package com.cognixia.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cognixia.model.Assignment;

import jakarta.persistence.PreRemove;
import jakarta.transaction.Transactional;

@Repository
public interface AssignmentRepo extends JpaRepository<Assignment, Integer> {
	@Query(value ="SELECT a.* FROM assignment a LEFT JOIN course c ON a.course_id = c.id WHERE a.student_id = ?1 AND c.course_name=?2", nativeQuery = true)
	public List<Assignment> getAssignments(Integer studentId, String courseName);
	
	//@Query(value ="SELECT -1 AS id, (SUM(a.grade * a.weight) / SUM(a.weight)) AS grade, -1 AS weight, u.username AS student_name, a.student_id, a.course_id FROM assignment a LEFT JOIN user u ON a.student_id = u.id WHERE a.course_id=?1 GROUP BY a.student_id", nativeQuery = true)
	@Query(value ="SELECT u.id AS id, (SUM(a.grade * a.weight) / SUM(a.weight)) AS grade, -1 AS weight, u.username AS student_name, a.student_id, a.course_id FROM assignment a LEFT JOIN user u ON a.student_id = u.id WHERE a.course_id=?1 GROUP BY a.student_id", nativeQuery = true)
	public List<Assignment> getStudents(Integer CourseId);
	
	@PreRemove
	@Transactional
	@Modifying
	@Query(value ="DELETE FROM assignment a WHERE a.student_id = ?1 AND a.course_id = ?2", nativeQuery = true)
	public int removeStudent(Integer studentId, Integer CourseId); // switch return value to void if broken
}
