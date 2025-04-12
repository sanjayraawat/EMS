package com.example.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="organization_data")

	public class Organization
	{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

		private String name;
	    private String email;
	    private String phoneNumber;
	    private String address;
	    private String website;

	    // Constructor
	    public Organization() {}

	    // Getter and Setter for name
	    public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }

	    // Getter and Setter for email
	    public String getEmail() {
	        return email;
	    }

	    public void setEmail(String email) {
	        this.email = email;
	    }

	    // Getter and Setter for phoneNumber
	    public String getPhoneNumber() {
	        return phoneNumber;
	    }

	    public void setPhoneNumber(String phoneNumber) {
	        this.phoneNumber = phoneNumber;
	    }

	    // Getter and Setter for address
	    public String getAddress() {
	        return address;
	    }

	    public void setAddress(String address) {
	        this.address = address;
	    }

	    // Getter and Setter for website
	    public String getWebsite() {
	        return website;
	    }

	    public void setWebsite(String website) {
	        this.website = website;
	    }

	    // toString method
	    @Override
	    public String toString() {
	        return "Organization{" +
	               "name='" + name + '\'' +
	               ", email='" + email + '\'' +
	               ", phoneNumber='" + phoneNumber + '\'' +
	               ", address='" + address + '\'' +
	               ", website='" + website + '\'' +
	               '}';
	    }
	}


