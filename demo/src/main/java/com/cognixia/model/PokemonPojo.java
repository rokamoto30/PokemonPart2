package com.cognixia.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties
public class PokemonPojo {
	String name;
	String url;
	
	public PokemonPojo() {}

	public PokemonPojo(String name, String url) {
		super();
		this.name = name;
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "PokemonPojo [name=" + name + ", url=" + url + "]";
	}

	
}
