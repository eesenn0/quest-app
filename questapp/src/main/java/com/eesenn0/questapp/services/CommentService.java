package com.eesenn0.questapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.eesenn0.questapp.entities.Comment;
import com.eesenn0.questapp.repos.CommentRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CommentService {
    
    private CommentRepository commentRepository;

    public List<Comment> getAllComments(Optional<Long> postId, Optional<Long> userId) {
        if (userId.isPresent() && postId.isPresent()) {
            return commentRepository.findByPostIdAndUserId(postId.get(), userId.get());
        } else if (userId.isPresent()) {
            return commentRepository.findByUserId(userId.get());
        } else if (postId.isPresent()) {
            return commentRepository.findByPostId(postId.get());
        }

        return commentRepository.findAll();
    }

    public Comment getOneCommentById(Long commentId) {
        return commentRepository.findById(commentId).orElse(null);
    }


}
