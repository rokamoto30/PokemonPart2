package com.cognixia.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognixia.exception.InvalidException;
import com.cognixia.model.Caught;
import com.cognixia.model.Pokemon;
import com.cognixia.model.User;
import com.cognixia.repo.UserRepo;

@Service
public class UserService {
	
	@Autowired
	UserRepo userRepo;
	
	public User getUser(String username) throws InvalidException { //Prob should cnahe to invalid USer exception
		Optional<User> found = userRepo.findByUsername(username);
		if (found.isEmpty()) {
			throw new InvalidException("User doesn't exist"); 
		}
		return found.get();
	}
	
	public User login(String username, String password) throws InvalidException {
		User user = getUser(username);
		if (user.getPassword().equals(password)) {
			return user;
		} else {
			throw new InvalidException("Password doesn't exist");
		}
	}
	
	public User createUser(String username, String password) throws InvalidException {
		Optional<User> found = userRepo.findByUsername(username);
		if (found.isPresent()) {
			throw new InvalidException("User already exist"); 
		}
		User user = new User(null, username, password, null);
		User created = userRepo.save(user);
		return created;
	}
	
	
	

}
