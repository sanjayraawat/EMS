package com.example.demo.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.User;

import jakarta.transaction.Transactional;



public interface UserRepository extends JpaRepository<User, Long> 
{
	
	Optional<User> findByEmail(String email);
	@Modifying
	@Transactional
	@Query("UPDATE User u SET u.username = :username WHERE u.email = :email")
	int updateUsernameByEmail(@Param("email") String email, @Param("username") String username);
	
	}
