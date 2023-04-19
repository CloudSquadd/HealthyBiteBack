package com.esprit.pidev.services.ForumServices;


import com.esprit.pidev.entities.Forum.LikeEntity;
import com.esprit.pidev.entities.UserRole.User;

public interface ILike {

    void likePost(User userId, Long postId);

    void unlikePost( Long postId);
    void likeComment(User userId, Long commentId);

    void unlikeComment(User userId, Long commentId);
}
