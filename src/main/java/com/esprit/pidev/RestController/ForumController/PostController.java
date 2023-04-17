package com.esprit.pidev.RestController.ForumController;

import com.esprit.pidev.entities.Forum.Post;
import com.esprit.pidev.services.ForumServices.IPost;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequestMapping("/api/test")
@RestController
@AllArgsConstructor
public class PostController {

    private final IPost iPost;

    @PostMapping("/addPost")
    public Post addPost(@RequestBody Post pt){
        return iPost.addPost(pt);
    }

    @PutMapping("/updatePost/{id}")
    public Post updatePost(@PathVariable("id") Long id, @RequestBody Post pt){
        return iPost.updatePost(id, pt);
    }

    @GetMapping("/getPostById/{id}")
    public Post retrievePostById(@PathVariable("id") Long id){
        return iPost.retrievePostById(id);
    }

    @GetMapping("/getAllPost")
    public List<Post> retrieveAllPost(){
        return iPost.findAll();
    }




    @DeleteMapping("/deletePost/{id}")
    public void deletePost(@PathVariable("id") Long id){
        iPost.deletePost(id);
    }
}
