package com.cognixia.pokemonApiRunner.service;


import com.cognixia.pokemonApiRunner.model.Caught;
import com.cognixia.pokemonApiRunner.model.Pokemon;
import com.cognixia.pokemonApiRunner.network.ApiException;
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
	
	public static Caught level(String username, String pokemonName, Integer level) throws Exception {
		String endpoint = String.format("/caught/level/%s/%s/%s", username, pokemonName, level);
		String response = Request.send(endpoint, "PUT");
		return Request.parse(response, Caught.class);	
	}
	
	public static Pokemon[] getAll() throws Exception {
		String endpoint = String.format("/caught/getAll");
		String response = Request.send(endpoint, "GET");
		return Request.parse(response, Pokemon[].class);	
	}
	
	public static Pokemon[] getUncaught(String username) throws Exception {
		String endpoint = String.format("/caught/getUncaught/%s", username);
		String response = Request.send(endpoint, "GET");
		return Request.parse(response, Pokemon[].class);	
	}
	
	public static Caught[] getUncompleted(String username) throws Exception {
		String endpoint = String.format("/caught/getUncompleted/%s", username);
		String response = Request.send(endpoint, "GET");
		return Request.parse(response, Caught[].class);	
	}
	
	public static Caught catchPokemon(String username, String pokemonName, Integer level) throws Exception {
		String endpoint = String.format("/caught/catch/%s/%s/%s", username, pokemonName, level);
		String response = Request.send(endpoint, "POST");
		return Request.parse(response, Caught.class);	
	}
	
	public static void populate() throws ApiException {
		String endpoint = String.format("/caught/populate");
		Request.send(endpoint, "POST");
	}
	
	public static void level() throws Exception {
		String endpoint = String.format("/caught/truncate");
		Request.send(endpoint, "DELETE");
	}
}
