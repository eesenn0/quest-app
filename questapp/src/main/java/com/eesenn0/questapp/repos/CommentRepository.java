package com.eesenn0.questapp.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eesenn0.questapp.entities.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByPostId(Long postId);

    List<Comment> findByUserId(Long userId);

    List<Comment> findByPostIdAndUserId(Long postId, Long userId);
    
}
