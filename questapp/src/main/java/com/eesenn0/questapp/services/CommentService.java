package com.eesenn0.questapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.eesenn0.questapp.entities.Comment;
import com.eesenn0.questapp.entities.Post;
import com.eesenn0.questapp.entities.User;
import com.eesenn0.questapp.repos.CommentRepository;
import com.eesenn0.questapp.requests.CommentCreateRequest;
import com.eesenn0.questapp.requests.CommentUpdateRequest;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CommentService {
    
    private CommentRepository commentRepository;
    private UserService userService;
    private PostService postService;

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

    public Comment createOneComment(CommentCreateRequest newCommentRequest) {
        Post post = postService.getOnePostById(newCommentRequest.getPostId());
        User user = userService.getOneUser(newCommentRequest.getUserId());

        if (user == null && post == null) {
            return null;
        }

        Comment aComment = new Comment();
        aComment.setId(newCommentRequest.getId());
        aComment.setText(newCommentRequest.getText());
        aComment.setUser(user);
        aComment.setPost(post);

        return commentRepository.save(aComment);
    }

    public Comment updateOneCommentById(Long commentId, CommentUpdateRequest updateComment) {
        Optional<Comment> comment = commentRepository.findById(commentId);

        if (comment.isPresent()) {
            Comment toUpdateComment = comment.get();
            toUpdateComment.setText(updateComment.getText());
            commentRepository.save(toUpdateComment);
            return toUpdateComment;
        } else
            return null;
    }

    public void deleteOneCommentById(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}
