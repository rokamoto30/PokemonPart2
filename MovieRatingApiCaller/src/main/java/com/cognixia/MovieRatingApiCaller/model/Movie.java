package com.cognixia.MovieRatingApiCaller.model;

import java.util.List;

public class Movie {
	
	
	private Integer id;
	
	private String name;
	
	private Double rating;
	
	private Integer count;
	
    private List<Rating> ratings;

	
	public Movie(){}
	
	public Movie(Integer id, String name, Double rating, Integer count, List<Rating> ratings) {
		super();
		this.id = id;
		this.name = name;
		this.rating = rating;
		this.count = count;
		this.ratings = ratings;
	}

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Rating> getRatings() {
		return ratings;
	}

	public void setRatings(List<Rating> ratings) {
		this.ratings = ratings;
	}

	@Override
	public String toString() {
		return "Movie [id=" + id + ", name=" + name + ", ratings=" + ratings + "]";
	}
	
}
