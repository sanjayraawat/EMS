package com.example.demo.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.User;

@Service
public class HomeService 
{
	@Autowired
	private com.example.demo.repo.UserRepository userRepository;
	
	public boolean addUser(User user, MultipartFile photo) {
	    try {
	        // Read the uploaded photo as bytes and set it in the user object
	        byte[] photoBytes = photo.getBytes();
	        user.setPhoto(photoBytes);

	        // Save the user to the repository
	        userRepository.save(user);
	        return true;

	    } catch (IOException e) {
	        // Log the exception (optional: use a logging framework like SLF4J)
	        System.err.println("Failed to process uploaded photo: " + e.getMessage());
	        return false;
	    }
	}

	
	public List<User> getAllUsers() {
		return userRepository.findAll();
		
	}
	
	public Optional<User> validateUser(String email) {
		return userRepository.findByEmail(email);
		}

	public User getUserById(String id) {
		// TODO Auto-generated method stub
		return null;
	}
	
   

    public void changeUsernameByEmail(String email, String newUsername) {
        int updatedRows = userRepository.updateUsernameByEmail(email, newUsername);
        if (updatedRows == 0) {
            throw new RuntimeException("No user found with email: " + email);
        }
    }
}
