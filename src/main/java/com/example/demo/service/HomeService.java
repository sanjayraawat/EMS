package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.User;
import com.example.demo.repo.UserRepository;

@Service
public class HomeService {
	@Autowired
	private UserRepository userRepository;
	public boolean addUser(User user) {
		userRepository.save(user);
		return true;
	}
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}
	public  Optional<User> validateUser(String email) {
		return userRepository.findByEmail(email);
	}
	
	public boolean updateUser(User user) {
		userRepository.updateUsernameByEmail(user.getEmail(), user.getUsername());
		return true;
	}
}
