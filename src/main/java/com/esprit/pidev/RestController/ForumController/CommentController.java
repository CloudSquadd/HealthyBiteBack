package com.esprit.pidev.RestController.ForumController;

import com.esprit.pidev.entities.Forum.Comment;
import com.esprit.pidev.entities.Forum.Post;
import com.esprit.pidev.services.ForumServices.IComment;
import com.esprit.pidev.services.ForumServices.IPost;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class CommentController {

    IComment iComment;

    @PostMapping("/addComment")
    public Comment addComment(@RequestBody Comment cmt){
        return iComment.addComment(cmt);
    }

    @PutMapping("/updateComment")
    public Comment updateComment(@RequestBody Comment cmt){
        return iComment.updateComment(cmt);
    }

    @GetMapping("getCommentById/{id}")
    public Comment retrieveCommentById(@PathVariable("id") Long id){
        return iComment.retrieveCommentById(id);
    }


    @GetMapping("/getAllComment")
    public List<Comment> retrieveAllComment(){
        return iComment.retrieveAllComments();
    }


    @DeleteMapping("deleteComment/{id}")
    public void deleteComment(@PathVariable("id") Long id){
        iComment.deleteComment(id);
    }

}
