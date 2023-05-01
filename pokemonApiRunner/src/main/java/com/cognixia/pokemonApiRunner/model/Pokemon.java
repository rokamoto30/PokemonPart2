package com.cognixia.pokemonApiRunner.model;

import java.io.Serializable;
import java.util.List;


public class Pokemon implements Serializable{

	private static final long serialVersionUID = 1L;

	private Integer id;
	
	private String pokemonName;
	

	
	public Pokemon() {}

	public Pokemon(Integer id, String pokemonName) {
		super();
		this.id = id;
		this.pokemonName = pokemonName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPokemonName() {
		return pokemonName;
	}

	public void setPokemonName(String pokemonName) {
		this.pokemonName = pokemonName;
	}

	
	
	
	
}
