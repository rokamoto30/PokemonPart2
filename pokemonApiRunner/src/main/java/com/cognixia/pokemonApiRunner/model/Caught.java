package com.cognixia.pokemonApiRunner.model;

import java.io.Serializable;

public class Caught implements Serializable{

	private static final long serialVersionUID = 1L;
	

	private Integer id;

	private int level; 
	

	private boolean completed;
	
	private String pokemonName;

	public Caught() {}

	public Caught(Integer id, int level, boolean completed, String pokemonName) {
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

	public String getPokemonName() {
		return pokemonName;
	}

	public void setPokemonName(String pokemonName) {
		this.pokemonName = pokemonName;
	}

	
}
