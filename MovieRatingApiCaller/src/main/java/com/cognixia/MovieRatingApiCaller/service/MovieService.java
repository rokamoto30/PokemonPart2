package com.cognixia.MovieRatingApiCaller.service;

import com.cognixia.MovieRatingApiCaller.model.Movie;
import com.cognixia.MovieRatingApiCaller.network.ApiException;
import com.cognixia.MovieRatingApiCaller.network.Request;

public class MovieService {
	public static void moviesDriver() {
		try {
			Movie[] movies = MovieService.getMovies();
			System.out.println(MovieService.tableFormat(movies));
		} catch (ApiException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void validMovieDriver() {
		try {
			Movie[] movies = MovieService.getMoviesValid();
			System.out.println(MovieService.tableFormat(movies));
		} catch (ApiException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static Movie[] getMovies() throws Exception {
		String endpoint = String.format("/movie");
		String response = Request.send(endpoint, "GET");
		return Request.parse(response, Movie[].class);		
	}
	public static Movie[] getMoviesValid() throws Exception {
		String endpoint = String.format("/movie/valid");
		String response = Request.send(endpoint, "GET");
		return Request.parse(response, Movie[].class);		
	}
	public static String prettyString(Movie[] movies) {
		StringBuilder prettyPrint = new StringBuilder();
		for (Movie movie : movies) {
			prettyPrint.append(movie.getName() + ": " + movie.getRating() + " from " + movie.getCount() + " ratings\n");
		}
		return prettyPrint.toString();
	}
	public static StringBuilder tableFormat(Movie[] movies) {
		int maxNameLen = "Movie Name".length();
		int maxRatingLen = "Rating".length();
		int maxCountLen = "Count".length();
		StringBuilder table = new StringBuilder();
		for (Movie movie : movies) {
			if (movie.getName() != null && movie.getName().length() + 1 > maxNameLen) {
				maxNameLen = movie.getName().length() + 1;
			}
			if (movie.getRating() != null && movie.getRating().toString().length() + 1 > maxRatingLen) {
				maxRatingLen = movie.getRating().toString().length() + 1;
			}
			if (movie.getCount() != null && movie.getCount().toString().length() + 1 > maxCountLen) {
				maxCountLen = movie.getCount().toString().length() + 1;
			}
		}
		String header = String.format("| %-" + maxNameLen +"s | %-" + maxRatingLen +"s | %-" + maxCountLen + "s|\n", "Movie Name", "Rating", "Count");
		String breaker = "=".repeat(header.length()) + "\n";
		table.append(breaker);
		table.append(header);
		table.append(breaker);
		for (Movie movie : movies) {
			table.append(String.format("| %-" + maxNameLen +"s | %-" + maxRatingLen +"s | %-" + maxCountLen + "s|\n", movie.getName(), movie.getRating(), movie.getCount()));
		}
		table.append(breaker);
		return table;
		
		
	}
}
