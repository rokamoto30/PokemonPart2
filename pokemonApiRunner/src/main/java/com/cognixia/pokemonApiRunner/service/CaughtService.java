package com.cognixia.pokemonApiRunner.service;


import java.lang.reflect.Field;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.cognixia.pokemonApiRunner.model.Caught;
import com.cognixia.pokemonApiRunner.model.Pokemon;
import com.cognixia.pokemonApiRunner.network.ApiException;
import com.cognixia.pokemonApiRunner.network.Request;


public class CaughtService {
	public static void appPrint(String text) {
		System.out.println(TextColor.greenText() + text + TextColor.clearText() + ":");
		TextColor.blue();
	}
	
	public static Caught[] getCollection(String username) throws Exception {
		if(username == null) {
			throw new ApiException("Must be logged int ot use this method!");
		}
		String endpoint = String.format("/caught/getCollection/%s", username);
		String response = Request.send(endpoint, "GET");
		return Request.parse(response, Caught[].class);	
	}
	
	public static Caught search(Scanner sc, String username) throws Exception {
		if(username == null) {
			throw new ApiException("Must be logged int ot use this method!");
		}
		appPrint("Enter pokemon name");
		String pokemonName = sc.nextLine();
		TextColor.clear();
		String endpoint = String.format("/caught/search/%s/%s", username, pokemonName);
		String response = Request.send(endpoint, "GET");
		return Request.parse(response, Caught.class);	
	}
	
	public static Caught level(Scanner sc, String username) throws Exception {
		if(username == null) {
			throw new ApiException("Must be logged int ot use this method!");
		}
		
		appPrint("Enter pokemon name");
		String pokemonName = sc.nextLine();
		appPrint("Enter pokemon level");
		Integer level = sc.nextInt();
		sc.nextLine();
		TextColor.clear();
		
		String endpoint = String.format("/caught/level/%s/%s/%s", username, pokemonName, level);
		String response = Request.send(endpoint, "PUT");
		return Request.parse(response, Caught.class);	
	}
	
	public static Caught[] getUncompleted(String username) throws Exception {
		if(username == null) {
			throw new ApiException("Must be logged int ot use this method!");
		}
		String endpoint = String.format("/caught/getUncompleted/%s", username);
		String response = Request.send(endpoint, "GET");
		return Request.parse(response, Caught[].class);	
	}
	
	public static Caught catchPokemon(Scanner sc, String username) throws Exception {
		if(username == null) {
			throw new ApiException("Must be logged int ot use this method!");
		}
		appPrint("Enter pokemon name");
		String pokemonName = sc.nextLine();
		appPrint("Enter pokemon level");
		Integer level = sc.nextInt();
		sc.nextLine();
		TextColor.clear();
		
		String endpoint = String.format("/caught/catch/%s/%s/%s", username, pokemonName, level);
		String response = Request.send(endpoint, "POST");
		return Request.parse(response, Caught.class);	
	}
	
	
	public static Pokemon[] getAll() throws Exception {
		String endpoint = String.format("/caught/getAll");
		String response = Request.send(endpoint, "GET");
		return Request.parse(response, Pokemon[].class);	
	}
	
	public static Pokemon[] getUncaught(String username) throws Exception {
		if(username == null) {
			throw new ApiException("Must be logged int ot use this method!");
		}
		String endpoint = String.format("/caught/getUncaught/%s", username);
		String response = Request.send(endpoint, "GET");
		return Request.parse(response, Pokemon[].class);	
	}
	
	public static void populate() throws ApiException {
		String endpoint = String.format("/caught/populate");
		Request.send(endpoint, "POST");
	}
	
	public static void truncate() throws Exception {
		String endpoint = String.format("/caught/truncate");
		Request.send(endpoint, "DELETE");
	}
	
	public static <T> StringBuilder tableFormat(Caught[] caught){
//		List<Integer> columLen = columnNames.stream().map(n -> n.length()).collect(Collectors.toList());
//		StringBuilder table = new StringBuilder();
//		for (T row : args) {
//			 Field[] fields = row.getClass().getDeclaredFields();
//		        for (Field field : fields) {
//		        	field.get(row).toString().length();
//		        	System.out.println(field);
//		        	return null;
//		        }
		StringBuilder table = new StringBuilder();
		String level = "level";
		String name = "name";
		String completed = "completed";
		int maxLevelLen = level.length();
		int maxNameLen = name.length();
		int maxCompletedLen = completed.length();
		
		for (Caught pokemon : caught) {
			if (pokemon.getPokemonName() != null && pokemon.getPokemonName().length() + 2 > maxNameLen) {
				maxNameLen = pokemon.getPokemonName().length() + 2;
			}
			if (pokemon.getLevel() != null && pokemon.getLevel().toString().length() + 2 > maxLevelLen) {
				maxLevelLen = pokemon.getLevel().toString().length() + 2;
			}
			if (pokemon.isCompleted() != null && pokemon.isCompleted().toString().length() + 2 > maxCompletedLen) {
				maxCompletedLen = pokemon.isCompleted().toString().length() + 2;
			}
		}
		
		String header = String.format("| " + TextColor.yellowText() + "%-" + maxNameLen +"s " + TextColor.clearText() + "| " + TextColor.greenText() + "%-" + maxLevelLen +"s " + TextColor.clearText() + "| " + TextColor.greenText() + "%-" + maxCompletedLen + "s " + TextColor.clearText() + "|\n", name, level, completed);
		String breaker = "=".repeat(header.length()-1-(6*5)) + "\n";
		table.append(breaker);
		table.append(header);
		table.append(breaker);
		for (Caught pokemon : caught) {
			if (pokemon.isCompleted()) {
				table.append(String.format("| " + TextColor.yellowText() + "%-" + maxNameLen +"s " + TextColor.clearText() + "| " + TextColor.greenText() + "%-" + maxLevelLen +"s " + TextColor.clearText() + "| " + TextColor.greenText() + "%-" + maxCompletedLen + "s " + TextColor.clearText() + "|\n", pokemon.getPokemonName(), pokemon.getLevel(), pokemon.isCompleted()));
			} else {
				table.append(String.format("| " + TextColor.yellowText() + "%-" + maxNameLen +"s " + TextColor.clearText() + "| " + TextColor.greenText() + "%-" + maxLevelLen +"s " + TextColor.clearText() + "| " + TextColor.redText() + "%-" + maxCompletedLen + "s " + TextColor.clearText() + "|\n", pokemon.getPokemonName(), pokemon.getLevel(), pokemon.isCompleted()));
			}
		}
		table.append(breaker);
		return table;
	}
}
