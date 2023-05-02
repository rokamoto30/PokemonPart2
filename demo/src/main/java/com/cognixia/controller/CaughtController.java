package com.cognixia.controller;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognixia.exception.InvalidException;
import com.cognixia.exception.MaxLevelException;
import com.cognixia.exception.PokemonNotFoundException;
import com.cognixia.model.Caught;
import com.cognixia.model.Pokemon;
import com.cognixia.service.CaughtService;

@RequestMapping("/api")
@RestController
public class CaughtController {
	@Autowired
	CaughtService service;
	
	
	@GetMapping("/caught/getCollection/{username}")
	public List<Caught> getCollection(@PathVariable String username) throws InvalidException {
		return service.getCollection(username);
	}
	
	@GetMapping("/caught/search/{username}/{pokemonName}")
	public Caught search(@PathVariable String username, @PathVariable String pokemonName) throws InvalidException, PokemonNotFoundException {
		return service.search(username, pokemonName);
	}
	
	@PutMapping("/caught/level/{username}/{pokemonName}/{level}")
	public Caught level(@PathVariable String username,  @PathVariable String pokemonName,  @PathVariable Integer level) throws InvalidException, MaxLevelException, PokemonNotFoundException {
		return service.level(username, pokemonName, level);
	}
	
	@GetMapping("/caught/getAll")
	public List<Pokemon> getAll() throws InvalidException {
		return service.getAllPokemon();
	}
	
	@GetMapping("/caught/getUncaught/{username}")
	public List<Pokemon> getUncaught(@PathVariable String username) throws InvalidException {
		return service.getUncaught(username);
	}
	@GetMapping("/caught/getUncompleted/{username}")
	public List<Caught> getUncompleted(@PathVariable String username) throws InvalidException {
		return service.getUncompleted(username);
	}
	
	@PostMapping("/caught/catch/{username}/{pokemonName}/{level}")
	public Caught catchPokemon(@PathVariable String username,  @PathVariable String pokemonName,  @PathVariable Integer level) throws InvalidException, PokemonNotFoundException, MaxLevelException {
		return service.catchPokemon(username, pokemonName, level);
	}
	
	@PostMapping("/caught/populate")
	public boolean populate() throws Exception {
		return service.populateDB();
	}
	
	@DeleteMapping("/caught/truncate")
	public void truncate() {
		service.truncate();
	}

}
