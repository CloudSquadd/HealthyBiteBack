package com.esprit.pidev.services.ForumServices;

import com.esprit.pidev.entities.Forum.Post;

import java.nio.file.AccessDeniedException;
import java.util.List;

public interface IPost {
    Post addPost(Post pt);

    Post updatePost(Long id, Post pt);

    Post retrievePostById(Long id);

/*
    List<Post> retrieveAllPost();
*/

    List<Post> retrieveAllPosts();



    void deletePost(Long id);

    List<Post> findAll();
}
