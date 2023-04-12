package com.esprit.pidev.services.ForumServices;


import com.esprit.pidev.entities.User;


public interface ILike {

    void likePost(User user, Long postId);

    void unlikePost(User user, Long postId);

    void likeComment(User user, Long commentId);

    void unlikeComment(User user, Long commentId);
}

