package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Organization;
import com.example.demo.User;
import com.example.demo.repo.OrganizationRepository;

@Service
public class OrganizationService
{
	@Autowired
	private com.example.demo.repo.OrganizationRepository organizationRepository;
	public boolean addOrganization(Organization organization)
	{
		organizationRepository.save(organization);
		return true;
		
	}
	
	public List<Organization> getAllOrganzations() {
		return organizationRepository.findAll();
		
	}
	
	public Optional<Organization> validateOrganization(String email) 
	{
		return organizationRepository.findByEmail(email);
		}
	
	public void changeOrganizationnameByEmail(String email, String name , String phoneNumber , String address , String website) {
	    int updatedRows = organizationRepository.updateOrganizationnameByEmail(email, name, phoneNumber , address , website);
	    if (updatedRows == 0) {
	        throw new RuntimeException("No user found with email: " + email);
	    }
	}

}
