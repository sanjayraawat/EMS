package com.example.demo.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.User;

public interface UserRepository extends JpaRepository<User, Long> {
	@Modifying
	@Transactional
	@Query("UPDATE User u SET u.username = :username WHERE u.email = :email")
	int updateUsernameByEmail(@Param("email") String email, @Param("username") String username);

	Optional<User> findByEmail(String email);
}
