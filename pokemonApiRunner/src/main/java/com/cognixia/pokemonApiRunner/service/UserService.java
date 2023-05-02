package com.cognixia.pokemonApiRunner.service;

import java.util.Scanner;

import com.cognixia.pokemonApiRunner.model.User;
import com.cognixia.pokemonApiRunner.network.ApiException;
import com.cognixia.pokemonApiRunner.network.Request;

public class UserService {
	public static void appPrint(String text) {
		System.out.println(TextColor.greenText() + text + TextColor.clearText() + ":");
		TextColor.blue();
	}

	public static User login(Scanner sc) throws Exception{
		appPrint("Enter username");
		String username = sc.nextLine();
		appPrint("Enter password");
		String password = sc.nextLine();
		TextColor.clear();
		String endpoint = String.format("/user/login/%s/%s", username, password);
		String response = Request.send(endpoint, "GET");
		return Request.parse(response, User.class);
	}
	
	
	public static User create(Scanner sc) throws Exception{
		appPrint("Enter username");
		String username = sc.nextLine();
		appPrint("Enter password");
		String password = sc.nextLine();
		appPrint("Confirm password");
		String passwordConfirm = sc.nextLine();
		TextColor.clear();

		if (!password.equals(passwordConfirm)) {
			throw new ApiException("password doesn't match");
		}
		String endpoint = String.format("/user/create/%s/%s", username, password);
		String response = Request.send(endpoint, "POST");
		return Request.parse(response, User.class);
	}
	
	
}
