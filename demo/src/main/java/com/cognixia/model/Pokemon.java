package com.cognixia.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Pokemon implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false)
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

	@Override
	public String toString() {
		return "Pokemon [id=" + id + ", pokemonName=" + pokemonName + "]";
	}
	
	
}
