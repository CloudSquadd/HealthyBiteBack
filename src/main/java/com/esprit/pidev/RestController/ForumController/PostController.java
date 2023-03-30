package com.esprit.pidev.RestController.ForumController;

import com.esprit.pidev.entities.Forum.Post;
import com.esprit.pidev.entities.RepasProduit.Fournisseur;
import com.esprit.pidev.services.ForumServices.IPost;
import com.esprit.pidev.services.RepasProduitServices.IFournisseur;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class PostController {

    IPost iPost;

    @PostMapping("/addPost")
    public Post addPost(@RequestBody Post pt){
        return iPost.addPost(pt);
    }

    @PutMapping("/updatePost")
    public Post updatePost(@RequestBody Post pt){
        return iPost.updatePost(pt);
    }

    @GetMapping("getPostById/{id}")
    public Post retrievePostById(@PathVariable("id") Long id){
        return iPost.retrievePostById(id);
    }


    @GetMapping("/getAllPost")
    public List<Post> retrieveAllPost(){
        return iPost.retrieveAllPost();
    }


    @DeleteMapping("deletePost/{id}")
    public void deletePost(@PathVariable("id") Long id){
        iPost.deletePost(id);
    }
}
