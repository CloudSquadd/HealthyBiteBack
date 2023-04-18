package com.esprit.pidev.services.ForumServices;

import com.esprit.pidev.entities.Forum.Category;
import com.esprit.pidev.entities.Forum.Post;
import com.esprit.pidev.entities.UserRole.User;
import com.esprit.pidev.repository.ForumRepository.PostRepository;
import com.esprit.pidev.repository.UserRoleRepository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class PostService implements IPost {
    PostRepository postRepository;
    UserRepository userRepository;

    @Override
    public Post addPost(Post pt, Long userId) {
        User retrievedUser = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("User with id " + userId + " not found"));
        pt.setUser(retrievedUser);
        return postRepository.save(pt);
    }




    @Override
    public Post updatePost(Long id, Post pt) {
        Post existingPost = postRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Post with id " + id + " not found"));

        existingPost.setTitle(pt.getTitle());
        existingPost.setContent(pt.getContent());
        existingPost.setImageName(pt.getImageName());
        existingPost.setUser(pt.getUser());
        //existingPost.setCategory(pt.getCategory());
        existingPost.setComments(pt.getComments());

        return postRepository.save(existingPost);
    }



    @Override
    public Post retrievePostById(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Post with id " + id + " not found"));
    }

/*    @Override
    public List<Post> retrieveAllPost() {
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        if (!response.isCommitted()) {
            return postRepository.findAll();
        } else {
            throw new RuntimeException("Could not get response");
        }
    }*/

    @Override
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    @Override
    public List<Post> retrieveAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }
}
