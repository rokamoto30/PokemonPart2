package com.cognixia.MovieRatingApiCaller.service;

import java.util.Scanner;

import com.cognixia.MovieRatingApiCaller.model.Rating;
import com.cognixia.MovieRatingApiCaller.network.ApiException;
import com.cognixia.MovieRatingApiCaller.network.Request;



public class RatingService {
	public static void ratingDriver(String curUser) {
		if (curUser.equals("guest")) {
			System.out.println("guests can't track ratings");
			return;
		}
		try {
			Rating[] ratings = getRatings(curUser);
			System.out.println(tableFormat(ratings));
		} catch (ApiException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static Rating createDriver(Scanner sc, String username) {
		try {
			System.out.println("Enter Movie Name:");
			String movie = sc.nextLine();
			System.out.println("Enter Rating:");
			Double rating = Double.valueOf(sc.nextLine());
			return create(username, movie, rating);
		} catch (ApiException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Rating updateDriver(Scanner sc, String username) {
		if (username.equals("guest")) {
			System.out.println("guests cant update ratings");
			return null;
		}
		try {
			System.out.println("Enter Movie Name:");
			String movie = sc.nextLine();
			System.out.println("Enter Rating:");
			Double rating = Double.valueOf(sc.nextLine());
			System.out.println("Do you want to favorite this movie? y/n:");
			String ans = sc.nextLine().toLowerCase();
			Boolean favorited = false;
			if (ans.equals("y")) {
				favorited = true;
			}
			return update(username, movie, rating, favorited);
		} catch (ApiException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Rating[] getRatings(String curUser) throws Exception {
		String endpoint = String.format("/rating/%s", curUser);
		String response = Request.send(endpoint, "GET");
		return Request.parse(response, Rating[].class);
	}
	
	public static Rating create(String username, String movie, Double rating) throws Exception {
		String endpoint = String.format("/rating/%s/%s/%s", username, movie, rating);
		String response = Request.send(endpoint, "POST");
		return Request.parse(response, Rating.class);		
	}
	
	public static Rating update(String curUser, String movie, Double rating, Boolean favorite) throws Exception {
		String endpoint = String.format("/rating/%s/%s/%s/%s", curUser, movie, rating, favorite);
		String response = Request.send(endpoint, "PUT");
		return Request.parse(response, Rating.class);		
	}
	
	public static StringBuilder tableFormat(Rating[] ratings) {
		int maxMovieLen = "Movie Name".length();
		int maxRatingLen = "Rating".length();
		int maxFavoriteLen = "Favorited".length();
		StringBuilder table = new StringBuilder();
		for (Rating rating : ratings) {
			if (rating.getMovie_name() != null && rating.getMovie_name().length() + 1 > maxMovieLen) {
				maxMovieLen = rating.getMovie_name().length() + 1 ;
			}
			if (rating.getRating() != null && rating.getRating().toString().length() + 1 > maxRatingLen) {
				maxRatingLen = rating.getRating().toString().length() + 1;
			}
			if (rating.getFavorite() != null && rating.getFavorite().toString().length() + 1 > maxFavoriteLen) {
				maxFavoriteLen = rating.getFavorite().toString().length() + 1;
			}
		}
		String header = String.format("| %-" + maxMovieLen +"s | %-" + maxRatingLen +"s | %-" + maxFavoriteLen + "s|\n", "Movie Name", "Rating", "Favorited");
		String breaker = "=".repeat(header.length()) + "\n";
		table.append(breaker);
		table.append(header);
		table.append(breaker);
		for (Rating rating : ratings) {
			table.append(String.format("| %-" + maxMovieLen +"s | %-" + maxRatingLen +"s | %-" + maxFavoriteLen + "s|\n", rating.getMovie_name(), rating.getRating().toString(), rating.getFavorite().toString()));
		}
		table.append(breaker);
		return table;
	}
}
