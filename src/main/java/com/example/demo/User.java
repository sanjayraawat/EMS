package com.example.demo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name="user_data")
public class User 
{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	private String username;
    private String email;
    private String password;
    private long organizationId;
    @Transient // This field won't be persisted in the database
    private String base64Photo;
    
    @Lob
    @Column(name = "photo", columnDefinition="LONGBLOB")
    private byte[] photo;
    
    public String getBase64Photo() {
        return base64Photo;
    }

    public void setBase64Photo(String base64Photo) {
        this.base64Photo = base64Photo;
    }
    @Transient
    public byte[] getPhoto() {
		return photo;
	}
	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}
	public long getOrganizationId() {
		return organizationId;
	}
	
	public void setOrganizationId(long organizationId) {
		this.organizationId = organizationId;
	}
	public User() {}
	public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
    
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}