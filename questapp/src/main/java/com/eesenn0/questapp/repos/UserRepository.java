package com.eesenn0.questapp.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eesenn0.questapp.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
    
}
