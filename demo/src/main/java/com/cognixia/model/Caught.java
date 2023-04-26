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
public class Caught implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = false)
	private int level; 
	

	@Column(nullable=false)
	private boolean completed;
	
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn( name = "pokemon_id", referencedColumnName = "id")
	private Pokemon pokemon;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn( name = "user_id", referencedColumnName = "id")
	private User username;

	public Caught() {}
	
	public Caught(Integer id, int level, boolean completed, Pokemon pokemon, User username) {
		super();
		this.id = id;
		this.level = level;
		this.completed = completed;
		this.pokemon = pokemon;
		this.username = username;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	public Pokemon getPokemon() {
		return pokemon;
	}

	public void setPokemon(Pokemon pokemon) {
		this.pokemon = pokemon;
	}

	public User getUsername() {
		return username;
	}

	public void setUsername(User username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "Caught [id=" + id + ", level=" + level + ", completed=" + completed + ", pokemon=" + pokemon
				+ ", username=" + username + "]";
	}
	
	
	
	
}
