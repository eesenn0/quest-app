package com.eesenn0.questapp.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eesenn0.questapp.entities.Like;

public interface LikeRepository extends JpaRepository<Like, Long> {
    
    List<Like> findLikesByUserId(Long userId);

    List<Like> findLikesByPostId(Long postId);

    List<Like> findLikesByPostIdAndUserId(Long postId, Long userId);
}
