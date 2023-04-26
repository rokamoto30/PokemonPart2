package com.cognixia.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Assignment implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable=false)
	private Double grade;
	
	@Column(nullable=false)
	private Double weight;
	
	private String studentName;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn( name = "course_id", referencedColumnName = "id")
	private Course course;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn( name = "student_id", referencedColumnName = "id")
	private User student;
	
	public Assignment() {}

	public Assignment(Integer id, Double grade, Double weight, String studentName, Course course, User student) {
		super();
		this.id = id;
		this.grade = grade;
		this.weight = weight;
		this.studentName = studentName;
		this.course = course;
		this.student = student;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getGrade() {
		return grade;
	}

	public void setGrade(Double grade) {
		this.grade = grade;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public User getStudent() {
		return student;
	}

	public void setStudent(User student) {
		this.student = student;
	}

	
}
