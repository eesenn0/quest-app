package com.eesenn0.questapp.requests;

import lombok.Data;

@Data
public class LikeCreateRequest {
    
    Long id;
    Long postId;
    Long userId;
}
