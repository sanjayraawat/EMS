package com.example.demo.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.Organization;
import com.example.demo.User;

import jakarta.transaction.Transactional;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {

    Optional<Organization> findByEmail(String email);

    @Modifying
    @Transactional
    @Query("UPDATE Organization u SET u.name = :name, u.phoneNumber = :phoneNumber, u.address = :address, u.website = :website WHERE u.email = :email")
    int updateOrganizationnameByEmail(
        @Param("email") String email,
        @Param("name") String name,
        @Param("phoneNumber") String phoneNumber,
        @Param("address") String address,
        @Param("website") String website
    );
}


