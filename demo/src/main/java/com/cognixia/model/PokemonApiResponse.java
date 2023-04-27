package com.cognixia.model;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.cognixia.model.PokemonPojo;

@JsonIgnoreProperties
public class PokemonApiResponse {
	int count;
	String next;
	String previous;
	PokemonPojo[] results;
	
	public PokemonApiResponse() {
		
	}
	
	public PokemonApiResponse(int count, String next, String previous, PokemonPojo[] results) {
		super();
		this.count = count;
		this.next = next;
		this.previous = previous;
		this.results = results;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getNext() {
		return next;
	}
	public void setNext(String next) {
		this.next = next;
	}
	public String getPrevious() {
		return previous;
	}
	public void setPrevious(String previous) {
		this.previous = previous;
	}
	public PokemonPojo[] getResults() {
		return results;
	}
	public void setResults(PokemonPojo[] results) {
		this.results = results;
	}
	@Override
	public String toString() {
		return "PokemonApiResponse [count=" + count + ", next=" + next + ", previous=" + previous + ", results="
				+ Arrays.toString(results) + "]";
	}
	
	
}
