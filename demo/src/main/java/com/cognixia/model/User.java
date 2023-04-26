package com.cognixia.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class User implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(unique=true, nullable = false)
	private String username;
	
	@Column(nullable = false)
	private String password;
	
	@JsonIgnore
	@OneToMany(mappedBy = "caught", cascade=CascadeType.ALL)
	private List<Caught> caught;
	
	public User() {}

	public User(Integer id, String username, String password, List<Caught> caught) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.caught = caught;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Caught> getCaught() {
		return caught;
	}

	public void setCaught(List<Caught> caught) {
		this.caught = caught;
	}

	
	
	
}
