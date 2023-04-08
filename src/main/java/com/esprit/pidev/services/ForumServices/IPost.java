package com.esprit.pidev.services.ForumServices;

import com.esprit.pidev.entities.Forum.Post;

import java.util.List;

public interface IPost {
    Post addPost(Post pt);
    Post updatePost(Post pt);
    Post retrievePostById(Long id);
    List<Post> retrieveAllPost();
    void deletePost(Long id);
}
