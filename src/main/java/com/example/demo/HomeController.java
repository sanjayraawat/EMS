package com.example.demo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.service.HomeService;

@Controller
public class HomeController {



	@Autowired
	private HomeService homeService;



	@GetMapping("/user")
	public String home(Model model) {
		model.addAttribute("name", "Spring Boot + Thymeleaf");
		return "index"; 
	}

	@PostMapping("/editUser")
	public  String editUser(@ModelAttribute User user) {
		System.out.println("Username: " + user.toString());
		homeService.updateUser(user);
		return "redirect:/users";
	}

	@PostMapping("/userCreation")
	public  ResponseEntity<?> createUser(@ModelAttribute User user) {
		System.out.println("Username: " + user.toString());
		Optional<User> dbu1= homeService.validateUser(user.getEmail());
		if(dbu1.isEmpty()) {
			homeService.addUser(user);
			return ResponseEntity.ok(Map.of( "status", "success"));
		}
		else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
					.body(Map.of("status", "error"));
		}
	}
	@GetMapping("/updateUsername/{id}")
	public  String updateUsername(@PathVariable("id") String id, Model model) {
		System.out.println("id "+id);
		Optional<User> dbuser=homeService.validateUser(id);
		System.out.println("db user "+dbuser);
		model.addAttribute("user",dbuser.get());
		return "edit_user";
	}
	@GetMapping("/users")
	public String getUsers(Model model) {
		List<User> users = homeService.getAllUsers();
		model.addAttribute("users", users);
		return "user_list"; 
	}
	@GetMapping("/login")
	public String login(Model model) {
		return "login"; 
	}
	@PostMapping("/checkUser")
	public  ResponseEntity<?>  validateUser(@ModelAttribute User user, Model model) {
		System.out.println("user: " + user.toString());
		Optional<User> dbuser=homeService.validateUser(user.getEmail());
		System.out.println("db user: " + dbuser.toString());
		if(user.getPassword().equals(dbuser.get().getPassword())) {
			List<User> users = homeService.getAllUsers();
			return ResponseEntity.ok(Map.of( "status", "success","users", users));
		}
		else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
					.body(Map.of("error", "Invalid password!"));

		}
	}
}
