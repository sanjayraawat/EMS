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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.repo.OrganizationRepository;
import com.example.demo.service.HomeService;

@Controller
public class HomeController {
    
	
    

    @Autowired
    private HomeService homeService;
    @Autowired
    private OrganizationRepository organizationRepository;

 

    @GetMapping("/user")
    public String home(Model model) {
    	List<Organization> organizations = organizationRepository.findAll();
        model.addAttribute("name", "Spring Boot + Thymeleaf");
        model.addAttribute("organizations", organizations);
        return "index"; 
    }

    @PostMapping("/userCreation")
    @ResponseBody
    public Map<String, String> createUser(@ModelAttribute User user ,  @RequestParam("photo") MultipartFile photo) 
    {
    	
        System.out.println("Username: " + user.toString());
        Optional<User> dbUser = homeService.validateUser(user.getEmail());
        Map<String, String> response = new HashMap<>();

        if (dbUser.isEmpty()) {
            homeService.addUser(user,photo);
            response.put("status", "success");
            response.put("message", "User created successfully.");
        } else {
            response.put("status", "error");
            response.put("message", "Email already registered.");
        }

        return response;


        
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
    
    
    @GetMapping("/updateUsername/{id}")
    
    
	public  String updateUsername(@PathVariable("id") String id, Model model) {
    	 
    	System.out.println("id "+id);
		Optional<User> dbuser=homeService.validateUser(id);
		System.out.println("db user "+dbuser);
		model.addAttribute("user",dbuser.get());
       
           return "edit_user";

       
		
	}
    
    @PostMapping("/editUser")
    public String updateUsername(@RequestParam String email,
            @RequestParam String username,
            RedirectAttributes redirectAttributes) {

homeService.changeUsernameByEmail(email, username);
redirectAttributes.addFlashAttribute("message", "Username updated successfully!");
return "redirect:/users";
    }

}
