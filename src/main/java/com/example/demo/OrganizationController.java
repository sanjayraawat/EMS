package com.example.demo;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.repo.OrganizationRepository;
import com.example.demo.service.HomeService;
import com.example.demo.service.OrganizationService;

@Controller
public class OrganizationController

{
	@Autowired
    private OrganizationService organizationService;
	
	@GetMapping("/organization")
    public String login(Model model) {
        return "organization"; 
    }
	
	@PostMapping("/createOrganization")
	@ResponseBody
	public ResponseEntity<Map<String, String>> saveOrganization(@ModelAttribute Organization organization)
	{
		System.out.println("Oranizationname: " + organization.toString());
		
	        organizationService.addOrganization(organization);
	       
	       try {
	            organizationService.addOrganization(organization);
	            // ✅ Custom success message
	            return ResponseEntity.ok().body(Map.of("status", "success", "message", "✅ Organization added successfully!"));
	        } catch (Exception e) {
	            e.printStackTrace();
	            return ResponseEntity.status(500).body(Map.of("status", "error", "message", "❌ Failed to save organization."));
	        }
	    
	       
	      
		
		
	
}
	
	 @GetMapping("/organizations")
	 	 
     public String getOrganizations(Model model) {
		 
         List<Organization> organizations = organizationService.getAllOrganzations();
         model.addAttribute("organizations", organizations);
         return "organization_list"; 
     }
	 
	 @GetMapping("/updateorganization/{id}")
	    
	    
		public  String updatename(@PathVariable("id") String id, Model model) {
	    	 
	    	System.out.println("id "+id);
			Optional<Organization> dbuser=organizationService.validateOrganization(id);
			System.out.println("db user "+dbuser);
			model.addAttribute("organization",dbuser.get());
	       
	           return "edit_organization";

	       
			
		}
	 
	  @PostMapping("/editorganization")
	    public String updateOrganizationname(@RequestParam String email,
	    		@RequestParam String name,  @RequestParam String phoneNumber , @RequestParam String address , @RequestParam String website,
	            RedirectAttributes redirectAttributes) {

		  organizationService.changeOrganizationnameByEmail(email, name , phoneNumber , address , website);
	redirectAttributes.addFlashAttribute("message", "updated successfully!");
	return "redirect:/organizations";
	    }
	  
	
	  }

	

