package com.eesenn0.questapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.eesenn0.questapp.entities.Post;
import com.eesenn0.questapp.entities.User;
import com.eesenn0.questapp.repos.PostRepository;
import com.eesenn0.questapp.requests.PostCreateRequest;
import com.eesenn0.questapp.requests.PostUpdateRequest;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PostService {

    private PostRepository postRepository;
    private UserService userService;

    public List<Post> getAllPosts(Optional<Long> userId) {
        if (userId.isPresent()) {
            return postRepository.findByUserId(userId.get());
        }

        return postRepository.findAll();
    }

    public Post getOnePostById(Long postId) {
        return postRepository.findById(postId).orElse(null);
    }

    public Post createOnePost(PostCreateRequest newPostRequest) {
        User user = userService.getOneUser(newPostRequest.getUserId());

        if (user == null) {
            return null;
        }

        Post toSave = new Post();
        toSave.setId(newPostRequest.getId());
        toSave.setText(newPostRequest.getText());
        toSave.setTitle(newPostRequest.getTitle());
        toSave.setUser(user);

        return postRepository.save(toSave);
    }

    public Post updateOnePostById(Long postId, PostUpdateRequest updatePost) {
        Optional<Post> post = postRepository.findById(postId);

        if (post.isPresent()) {
            Post toUpdate = post.get();
            toUpdate.setTitle(updatePost.getTitle());
            toUpdate.setText(updatePost.getText());
            postRepository.save(toUpdate);
            return toUpdate;
        } else
            return null;
    }

    public void deleteOnePostById(Long postId) {
        postRepository.deleteById(postId);
    }
}
