package com.cognixia.MovieRatingApiCaller.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

import com.cognixia.MovieRatingApiCaller.model.User;
import com.cognixia.MovieRatingApiCaller.network.ApiException;
import com.cognixia.MovieRatingApiCaller.network.Request;


public class UserService {
	public static User login(String username, String password) throws Exception {
		String endpoint = String.format("/user/login/%s/%s", username, password);
		String response = Request.send(endpoint, "GET");
		return Request.parse(response, User.class);		
	}
	
	public static User create(String username, String password, String confirmPass, String email) throws Exception {
		if (!password.equals(confirmPass)) {
			throw new ApiException("password doesnt match");
		}
		String endpoint = String.format("/user/create/%s/%s/%s", username, password, email);
		String response = Request.send(endpoint, "POST");
		return Request.parse(response, User.class);		
	}
	
	public static User update(String curUser, String username, String password, String email) throws Exception {
		String endpoint = String.format("/user/update/%s/%s/%s/%s", curUser, username, password, email);
		String response = Request.send(endpoint, "PUT");
		return Request.parse(response, User.class);		
	}
	
	
	public static User createDriver(Scanner sc) {
		try {
			System.out.println("Enter Email:");
			String email = sc.nextLine();
			System.out.println("Enter Username:");
			String username = sc.nextLine();
			System.out.println("Enter Password:");
			String password = sc.nextLine();
			System.out.println("Confirm Password:");
			String confirmPass = sc.nextLine();
			return create(username, password, confirmPass, email);
		} catch (ApiException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
    	
    }
    
    public static User updateDriver(String curUser, Scanner sc) {
    	try {

			System.out.println("Enter new Username:");
			String username = sc.nextLine();
			System.out.println("Enter new Password:");
			String password = sc.nextLine();
			System.out.println("Enter new Email:");
			String email = sc.nextLine();
			return update(curUser, username, password, email);
		} catch (ApiException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
    }
}
