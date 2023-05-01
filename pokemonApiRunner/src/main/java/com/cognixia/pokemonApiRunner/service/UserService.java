package com.cognixia.pokemonApiRunner.service;

import java.util.Scanner;

import com.cognixia.pokemonApiRunner.model.User;
import com.cognixia.pokemonApiRunner.network.ApiException;
import com.cognixia.pokemonApiRunner.network.Request;

public class UserService {

	public static User login(Scanner sc) throws Exception{
		System.out.println("Enter username:");
		String username = sc.nextLine();
		System.out.println("Enter password:");
		String password = sc.nextLine();
		String endpoint = String.format("/user/login/%s/%s", username, password);
		String response = Request.send(endpoint, "GET");
		return Request.parse(response, User.class);
	}
	
	
	public static User create(Scanner sc) throws Exception{
		System.out.println("Enter username:");
		String username = sc.nextLine();
		System.out.println("Enter password:");
		String password = sc.nextLine();
		System.out.println("Confrim password:");
		String passwordConfirm = sc.nextLine();
		if (!password.equals(passwordConfirm)) {
			throw new ApiException("password doesn't match");
		}
		String endpoint = String.format("/user/create/%s/%s", username, password);
		String response = Request.send(endpoint, "POST");
		return Request.parse(response, User.class);
	}
	
	
}
