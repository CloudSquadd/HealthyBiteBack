package com.esprit.pidev.RestController.ForumController;


import com.esprit.pidev.entities.Forum.LikeEntity;
import com.esprit.pidev.entities.UserRole.User;
import com.esprit.pidev.services.ForumServices.ILike;
import com.esprit.pidev.services.ForumServices.LikeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RequestMapping("/api/test")
@RestController
public class LikeController {

     LikeService likeService;

    @PostMapping("/posts/{postId}/like")
    public ResponseEntity<?> likePost(@AuthenticationPrincipal User userId, @PathVariable Long postId) {
        if (likeService.isPostLikedByUser(userId, postId)) {
            return ResponseEntity.badRequest().body("Post already liked by user");
        }
        likeService.likePost(userId, postId);
        return ResponseEntity.ok().body("you liked the post");
    }

    @PostMapping("/posts/{postId}/unlike")
    public ResponseEntity<?> unlikePost(@AuthenticationPrincipal User userId, @PathVariable Long postId) {
        if (!likeService.isPostLikedByUser(userId, postId)) {
            return ResponseEntity.badRequest().body("Post not liked by user");
        }
        likeService.unlikePost(userId, postId);
        return ResponseEntity.ok().body("you unliked the post");
    }

    @PostMapping("/comments/{commentId}/like")
    public ResponseEntity<?> likeComment(@AuthenticationPrincipal User userId, @PathVariable Long commentId) {
        if (likeService.isCommentLikedByUser(userId, commentId)) {
            return ResponseEntity.badRequest().body("Comment already liked by user");
        }
        likeService.likeComment(userId, commentId);
        return ResponseEntity.ok().body("you liked the comment");
    }

    @PostMapping("/comments/{commentId}/unlike")
    public ResponseEntity<?> unlikeComment(@AuthenticationPrincipal User userId, @PathVariable Long commentId) {
        if (!likeService.isCommentLikedByUser(userId, commentId)) {
            return ResponseEntity.badRequest().body("Comment not liked by user");
        }
        likeService.unlikeComment(userId, commentId);
        return ResponseEntity.ok().body("you unliked the comment");
    }


    @GetMapping("/posts/likes")
    public List<LikeEntity> getAllPostLikes() {
        return likeService.getAllPostLikes();
    }

    @GetMapping("/comments/likes")
    public List<LikeEntity> getAllCommentLikes() {
        return likeService.getAllCommentLikes();
    }




}
