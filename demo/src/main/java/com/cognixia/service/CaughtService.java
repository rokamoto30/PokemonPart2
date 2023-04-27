package com.cognixia.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognixia.model.Caught;
import com.cognixia.model.Pokemon;
import com.cognixia.model.User;
import com.cognixia.repo.CaughtRepo;
import com.cognixia.repo.PokemonRepo;

@Service
public class CaughtService {
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	CaughtRepo caughtRepo;
	
	@Autowired
	PokemonRepo pokemonRepo;
	
	public Pokemon getPokemon(String pokemonName) {
		Optional<Pokemon> found = pokemonRepo.getPokemonByName(pokemonName);
		if (found.isEmpty()) {
			throw //MISSING USER EXCEPTION
		}
		return found.get();
	}
	
	public Caught search(String username, String pokemonName) {
		User user = userService.getByUsername(username);
		Pokemon pokemon = getPokemon(pokemonName);
		Optional<Caught> found = caughtRepo.search(user.getId(), pokemon.getId());
		if (found.isEmpty()) {
			throw POKEMON NOT FOUND
		}
		return found.get();
	}
	
	public User getUser TODO MAYBE ADD IN USER SERVICE
	
	public List<Caught> getCollection(String username) {
		Optional<User> found = userService.getByUsername(username);
		if (found.isEmpty()) {
			throw //MISSING USER EXCEPTION
		}
		User user = found.get();
		
		caughtRepo.getCollection(user.getId());
	}
	
	public Caught catchPokemon(String username, String pokemonName, Integer level) {		
		Boolean completed = (level == 100);
		Caught newCatch = new Caught(null, level, completed, getPokemon(pokemonName), userService.getByUsername(username));
		Caught caught = caughtRepo.save(newCatch);
		return caught;
	}
	
	public Caught level(String username, String pokemonName, Integer level) {		
		Boolean completed = (level == 100);
		Caught caught = search(username, pokemonName);
		caught.setLevel(level);
		caught.setCompleted(completed);
		Caught updated = caughtRepo.save(caught);
		return updated;
	}
	
	public List<Pokemon> getAllPokemon() {
		return pokemonRepo.getAll();
	}
	
	public List<Pokemon> getUncaught(String username) {
		User user = userService.getByUsername(username);
		return pokemonRepo.getUncaught(user.getId());
	}
	
	public List<Caught> getUncompleted(String username) {
		User user = userService.getByUsername(username);
		return caughtRepo.getUncompleted(user.getId());
	}
	
	
}
