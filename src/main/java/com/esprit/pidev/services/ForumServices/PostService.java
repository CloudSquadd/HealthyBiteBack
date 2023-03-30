package com.esprit.pidev.services.ForumServices;

import com.esprit.pidev.entities.Forum.Post;
import com.esprit.pidev.repository.ForumRepository.PostRepository;

import java.util.List;

public class PostService implements IPost{

    PostRepository postRepository;
    @Override
    public Post addPost(Post pt) {
        return postRepository.save(pt);
    }

    @Override
    public Post updatePost(Post pt) {
        return postRepository.save(pt);
    }

    @Override
    public Post retrievePostById(Long id) {
        return postRepository.findById(id).orElse(null);
    }

    @Override
    public List<Post> retrieveAllPost() {
        return postRepository.findAll();
    }

    @Override
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }
}
