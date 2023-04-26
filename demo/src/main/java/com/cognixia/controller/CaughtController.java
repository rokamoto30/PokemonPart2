package com.cognixia.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api")
@RestController
public class CaughtController {
	
	
	@GetMapping("/caught/getCollection/{username}")
	public List<Caught> getCollection(@PathVariable String username) throws InvalidException {
		return service.getCollection(username);
	}
	
	@GetMapping("/caught/search/{username}/{pokemonName}")
	public Optional<Caught> search(@PathVariable String username, @PathVariable String pokemonName) throws InvalidException {
		return service.search(username, pokemonName);
	}
	
	@PostMapping("/caught/catch/{username}/{pokemonName}/{level}")
	public Optional<Caught> catch(@PathVariable String username,  @PathVariable String pokemonName,  @PathVariable Integer level) throws InvalidException {
		return service.catch(username, pokemonName, level);
	}
	
	@PutMapping("/caught/level/{username}/{pokemonName}/{level}")
	public Optional<Caught> level(@PathVariable String username,  @PathVariable String pokemonName,  @PathVariable Integer level) throws InvalidException {
		return service.level(username, pokemonName, level);
	}
	
	@GetMapping("/caught/getAll")
	public List<Pokemon> getAll() throws InvalidException {
		return pokemonService.getAll();
	}
	
	@GetMapping("/caught/getUncaught/{username}")
	public List<Pokemon> getUncaught(@PathVariable String username) throws InvalidException {
		return service.getUncaught(username);
	}
	@GetMapping("/caught/getUncompleted/{username}")
	public List<Caught> getUncompleted(@PathVariable String username) throws InvalidException {
		return service.getUncompleted(username);
	}
	
	

}
