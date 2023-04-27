package com.cognixia.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cognixia.model.Pokemon;


@Repository
public interface PokemonRepo extends JpaRepository<Pokemon, Integer>{
	@Query(value="SELECT p.* From pokemon p", nativeQuery = true)
	public List<Pokemon> getAll();
	
	@Query(value="SELECT p.* FROM pokemon WHERE p.id NOT IN(SELECT c.pokemon_id FROM caught c WHERE c.user_id = ?1) order by id", nativeQuery = true)
	public List<Pokemon> getUncaught(Integer id);
	
	@Query(value="SELECT p.* From pokemon p WHERE p.pokemonName = ?1", nativeQuery = true)
	public Optional<Pokemon> getPokemonByName(String pokemonName);
	
}
