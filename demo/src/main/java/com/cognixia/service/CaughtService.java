package com.cognixia.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognixia.exception.InvalidException;
import com.cognixia.exception.MaxLevelException;
import com.cognixia.exception.PokemonNotFoundException;
import com.cognixia.model.Caught;
import com.cognixia.model.Pokemon;
import com.cognixia.model.PokemonApiResponse;
import com.cognixia.model.PokemonPojo;
import com.cognixia.model.User;
import com.cognixia.repo.CaughtRepo;
import com.cognixia.repo.PokemonRepo;

import com.cognixia.connection.Request;

@Service
public class CaughtService {
	@Autowired
	UserService userService;
	
	@Autowired
	CaughtRepo caughtRepo;
	
	@Autowired
	PokemonRepo pokemonRepo;
	
	public boolean populateDB() throws Exception {
		truncate();
		String endpoint = "https://pokeapi.co/api/v2/pokemon?limit=151";
		String response = Request.send(endpoint, "GET");
//		System.out.println(response.split(System.lineSeparator()));
//		for (String line : response.split(",")) {
//			System.out.println(line);
//		}
		PokemonApiResponse apiResponse = Request.parse(response, PokemonApiResponse.class);
		PokemonPojo[] results = apiResponse.getResults();
		//System.out.println(pokemon[0]);
		for(PokemonPojo result : results) {
			pokemonRepo.save(new Pokemon(null, result.getName()));
		}
		
		return true;
	}
	
	public void truncate() {
		//caughtRepo.truncateTable();
		pokemonRepo.toggleFK(false);
		pokemonRepo.truncateTable();
		pokemonRepo.toggleFK(true);
	}
	
	public Boolean levelCheck(Integer level) throws MaxLevelException {
		if (level < 0) {
			throw new MaxLevelException("Level has to be at least 0");
		} else if (level > 100) {
			throw new MaxLevelException("Level has to be under 100");
		}
		return level == 100;
	}
	
	public Pokemon getPokemon(String pokemonName) throws PokemonNotFoundException {
		Optional<Pokemon> found = pokemonRepo.getPokemonByName(pokemonName);
		if (found.isEmpty()) {
			throw new PokemonNotFoundException("Pokemon not found");
		}
		return found.get();
	}
	
	public Caught search(String username, String pokemonName) throws PokemonNotFoundException, InvalidException {
		User user = userService.getUser(username);
		Pokemon pokemon = getPokemon(pokemonName);
		Optional<Caught> found = caughtRepo.search(user.getId(), pokemon.getId());
		if (found.isEmpty()) {
			throw new PokemonNotFoundException("Pokemon not found");
		}
		return found.get();
	}
	
	public Boolean pokemonCaught(String username, String pokemonName) throws InvalidException, PokemonNotFoundException {
		User user = userService.getUser(username);
		Pokemon pokemon = getPokemon(pokemonName);
		Optional<Caught> found = caughtRepo.search(user.getId(), pokemon.getId());
		return found.isPresent();
	}
	
	
	public List<Caught> getCollection(String username) throws InvalidException {
		User user = userService.getUser(username);
		return caughtRepo.getCollection(user.getId());
	}
	
	public Caught catchPokemon(String username, String pokemonName, Integer level) throws InvalidException, PokemonNotFoundException, MaxLevelException {		
		Boolean completed = levelCheck(level);
		if (pokemonCaught(username, pokemonName)) {
			throw new InvalidException("Pokemon has already been caught");
		}
		Caught newCatch = new Caught(null, level, completed, pokemonName, getPokemon(pokemonName), userService.getUser(username));
		Caught caught = caughtRepo.save(newCatch);
		return caught;
	}
	
	public Caught level(String username, String pokemonName, Integer level) throws InvalidException, MaxLevelException, PokemonNotFoundException {	
		Boolean completed = levelCheck(level);
		Caught caught = search(username, pokemonName);
		caught.setLevel(level);
		caught.setCompleted(completed);
		Caught updated = caughtRepo.save(caught);
		return updated;
	}
	
	public List<Pokemon> getAllPokemon() {
		return pokemonRepo.getAll();
	}
	
	public List<Pokemon> getUncaught(String username) throws InvalidException {
		User user = userService.getUser(username);
		return pokemonRepo.getUncaught(user.getId());
	}
	
	public List<Caught> getUncompleted(String username) throws InvalidException {
		User user = userService.getUser(username);
		return caughtRepo.getUncompleted(user.getId());
	}
	
	
}
