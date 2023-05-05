package com.esprit.pidev.services.ForumServices;


import com.esprit.pidev.entities.Forum.Comment;
import com.esprit.pidev.entities.Forum.LikeEntity;
import com.esprit.pidev.entities.Forum.LikeType;
import com.esprit.pidev.entities.Forum.Post;
import com.esprit.pidev.entities.UserRole.User;

public interface ILike {

    /*void likePost(User userId, Long postId);

    void unlikePost( Long postId);
    void likeComment(User userId, Long commentId);

    void unlikeComment(User userId, Long commentId);*/

    public Post ToggleLikesP (Long postId , LikeType likeType , Long userId);
    public Comment ToggleLikesC (Long commentId , LikeType likeType , Long userId);
}
