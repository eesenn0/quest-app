package com.eesenn0.questapp.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eesenn0.questapp.entities.Like;

public interface LikeRepository extends JpaRepository<Like, Long> {
    
}
