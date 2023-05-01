package com.cognixia.MovieRatingApiCaller.model;

public class Rating {

	private Integer id;
	
	private Double rating;
	
	private Boolean favorite;
	
	private String user_name;
	private String movie_name;
	
	public Rating() {}
	
	public Rating(Integer id, Double rating, Boolean favorite, String user_name, String movie_name) {
		super();
		this.id = id;
		this.rating = rating;
		this.favorite = favorite;
		this.user_name = user_name;
		this.movie_name = movie_name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}

	public Boolean getFavorite() {
		return favorite;
	}

	public void setFavorite(Boolean favorite) {
		this.favorite = favorite;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getMovie_name() {
		return movie_name;
	}

	public void setMovie_name(String movie_name) {
		this.movie_name = movie_name;
	}

	@Override
	public String toString() {
		return "Rating [id=" + id + ", rating=" + rating + ", favorite=" + favorite + ", user_name=" + user_name
				+ ", movie_name=" + movie_name + "]";
	}

	
}
