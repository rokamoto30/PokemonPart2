package com.cognixia.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cognixia.model.Caught;


@Repository
public interface CaughtRepo extends JpaRepository<Caught, Integer> {
	
	@Query(value="SELECT c.* FROM caught c WHERE c.user_id = ?1", nativeQuery = true)
	public List<Caught> getCollection(Integer id);
	
	@Query(value="SELECT c.* FROM caught c WHERE c.user_id = ?1 AND c.completed = false", nativeQuery = true)
	public List<Caught> getUncompleted(Integer id);
	
	@Query(value="SELECT c.* FROM caught c WHERE c.user_id = ?1 AND c.pokemon_id = ?2", nativeQuery = true)
	public Optional<Caught> search(Integer id, Integer pokemonId);

}
