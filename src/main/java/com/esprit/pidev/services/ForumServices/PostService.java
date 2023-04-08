package com.esprit.pidev.services.ForumServices;

import com.esprit.pidev.entities.Forum.Category;
import com.esprit.pidev.entities.Forum.Post;
import com.esprit.pidev.repository.ForumRepository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PostService implements IPost {
    @Autowired
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
        return postRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Post with id " + id + " not found"));
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
