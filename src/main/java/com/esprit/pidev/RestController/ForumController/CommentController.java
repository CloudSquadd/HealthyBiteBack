package com.esprit.pidev.RestController.ForumController;

import com.esprit.pidev.entities.Forum.Comment;
import com.esprit.pidev.entities.Forum.Post;
import com.esprit.pidev.entities.User;
import com.esprit.pidev.services.ForumServices.CommentService;
import com.esprit.pidev.services.ForumServices.IComment;
import com.esprit.pidev.services.ForumServices.IPost;
import com.esprit.pidev.services.ForumServices.PostService;
import com.esprit.pidev.services.UserRoleService.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController {

    private final CommentService commentService;
    private final PostService postService;

    @Autowired
    public CommentController(CommentService commentService, PostService postService) {
        this.commentService = commentService;
        this.postService = postService;
    }

    @PostMapping("/addComment")
    public ResponseEntity<Comment> addComment(@RequestBody Comment comment,
                                              @RequestParam Long postId) {
        Post post = postService.retrievePostById(postId);
        comment.setPost(post);
        Comment savedComment = commentService.addComment(comment);
        return ResponseEntity.ok(savedComment);
    }

    @GetMapping("/getPostComments")
    public ResponseEntity<List<Comment>> getPostComments(@RequestParam Long postId) {
        Post post = postService.retrievePostById(postId);
        List<Comment> comments = commentService.retrieveCommentsByPost(post);
        return ResponseEntity.ok(comments);
    }

    @GetMapping("/post/{postId}/comments")
    public List<Comment> getCommentsByPostId(@PathVariable Long postId) {
        Post post = postService.retrievePostById(postId);
        if(post == null){
            throw new IllegalArgumentException("Post not found");
        }
        return post.getComments();
    }

    @PutMapping("/updateComment")
    public Comment updateComment(@RequestBody Comment comment){
        return commentService.updateComment(comment);
    }

    @GetMapping("getCommentById/{id}")
    public Comment retrieveCommentById(@PathVariable("id") Long id){
        return commentService.retrieveCommentById(id);
    }

    @GetMapping("/getAllComment")
    public List<Comment> retrieveAllComment(){
        return commentService.retrieveAllComments();
    }

    @GetMapping("/getCommentsByPost/{postId}")
    public List<Comment> retrieveCommentsByPost(@PathVariable("postId") Long postId){
        Post post = postService.retrievePostById(postId);
        return commentService.retrieveCommentsByPost(post);
    }

    @DeleteMapping("deleteComment/{id}")
    public void deleteComment(@PathVariable("id") Long id){
        commentService.deleteComment(id);
    }
}
