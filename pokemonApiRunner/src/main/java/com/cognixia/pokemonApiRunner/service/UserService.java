package com.cognixia.pokemonApiRunner.service;

import com.cognixia.pokemonApiRunner.model.User;
import com.cognixia.pokemonApiRunner.network.Request;

public class UserService {

	public static User login(String username, String password) throws Exception{
		String endpoint = String.format("/user/login/%s/%s", username, password);
		String response = Request.send(endpoint, "GET");
		return Request.parse(response, User.class);
	}
	
	
	public static User create(String username, String password) throws Exception{
		String endpoint = String.format("/user/create/%s/%s", username, password);
		String response = Request.send(endpoint, "POST");
		return Request.parse(response, User.class);
	}
	
	
}
