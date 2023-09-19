package com.eesenn0.questapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.eesenn0.questapp.entities.Like;
import com.eesenn0.questapp.entities.Post;
import com.eesenn0.questapp.entities.User;
import com.eesenn0.questapp.repos.LikeRepository;
import com.eesenn0.questapp.requests.LikeCreateRequest;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LikeService {

    private LikeRepository likeRepository;
    private PostService postService;
    private UserService userService;

    public List<Like> getAllLikes(Optional<Long> postId,
            Optional<Long> userId) {
        if (userId.isPresent() && postId.isPresent()) {
            return likeRepository.findLikesByPostIdAndUserId(postId.get(), userId.get());
        } else if (userId.isPresent()) {
            return likeRepository.findLikesByUserId(userId.get());
        } else if (postId.isPresent()) {
            return likeRepository.findLikesByUserId(postId.get());
        }

        return likeRepository.findAll();
    }

    public Like getOneLikeById(Long likeId) {
        return likeRepository.findById(likeId).orElse(null);
    }

    public Like createOneLike(LikeCreateRequest newLikeRequest) {
        Post post = postService.getOnePostById(newLikeRequest.getPostId());
        User user = userService.getOneUser(newLikeRequest.getUserId());

        if (user != null && post != null) {
            Like aLike = new Like();
            aLike.setId(newLikeRequest.getUserId());
            aLike.setPost(post);
            aLike.setUser(user);

            return likeRepository.save(aLike);
        }

        return null;
    }

    public void deleteOneLikeById(Long likeId) {
        likeRepository.deleteById(likeId);
    }

}
