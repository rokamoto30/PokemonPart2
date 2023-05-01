package com.cognixia.pokemonApiRunner.service;


import com.cognixia.pokemonApiRunner.model.Caught;
import com.cognixia.pokemonApiRunner.network.Request;


public class CaughtService {
	public static Caught[] getCollection(String username) throws Exception {
		String endpoint = String.format("/caught/getCollection/%s", username);
		String response = Request.send(endpoint, "GET");
		return Request.parse(response, Caught[].class);	
	}
	
	public static Caught search(String username, String pokemonName) throws Exception {
		String endpoint = String.format("/caught/search/%s/%s", username, pokemonName);
		String response = Request.send(endpoint, "GET");
		return Request.parse(response, Caught.class);	
	}
}
