package com.eesenn0.questapp.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eesenn0.questapp.entities.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    
}
