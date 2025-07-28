package com.portal.jobportalproject.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.portal.jobportalproject.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    
	Optional<User> findByUsername(String username);

}