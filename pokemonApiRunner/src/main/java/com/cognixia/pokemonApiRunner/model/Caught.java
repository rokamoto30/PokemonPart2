package com.cognixia.pokemonApiRunner.model;

import java.io.Serializable;

public class Caught implements Serializable{

	private static final long serialVersionUID = 1L;
	

	private Integer id;

	private Integer level; 
	

	private Boolean completed;
	
	private String pokemonName;

	public Caught() {}

	public Caught(Integer id, Integer level, Boolean completed, String pokemonName) {
		super();
		this.id = id;
		this.level = level;
		this.completed = completed;
		this.pokemonName = pokemonName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Boolean isCompleted() {
		return completed;
	}

	public void setCompleted(Boolean completed) {
		this.completed = completed;
	}

	public String getPokemonName() {
		return pokemonName;
	}

	public void setPokemonName(String pokemonName) {
		this.pokemonName = pokemonName;
	}

	
}
