package com.esprit.pidev.RestController.ForumController;


import com.esprit.pidev.entities.Forum.LikeEntity;
import com.esprit.pidev.entities.UserRole.User;
import com.esprit.pidev.services.ForumServices.ILike;
import com.esprit.pidev.services.ForumServices.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/test")
@RestController
public class LikeController {

    @Autowired
    private LikeService likeService;

    @PostMapping("/posts/{postId}/like")
    public ResponseEntity<?> likePost(@AuthenticationPrincipal User user, @PathVariable Long postId) {
        if (likeService.isPostLikedByUser(user, postId)) {
            return ResponseEntity.badRequest().body("Post already liked by user");
        }
        likeService.likePost(user, postId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/posts/{postId}/unlike")
    public ResponseEntity<?> unlikePost(@AuthenticationPrincipal User user, @PathVariable Long postId) {
        if (!likeService.isPostLikedByUser(user, postId)) {
            return ResponseEntity.badRequest().body("Post not liked by user");
        }
        likeService.unlikePost(user, postId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/comments/{commentId}/like")
    public ResponseEntity<?> likeComment(@AuthenticationPrincipal User user, @PathVariable Long commentId) {
        if (likeService.isCommentLikedByUser(user, commentId)) {
            return ResponseEntity.badRequest().body("Comment already liked by user");
        }
        likeService.likeComment(user, commentId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/comments/{commentId}/unlike")
    public ResponseEntity<?> unlikeComment(@AuthenticationPrincipal User user, @PathVariable Long commentId) {
        if (!likeService.isCommentLikedByUser(user, commentId)) {
            return ResponseEntity.badRequest().body("Comment not liked by user");
        }
        likeService.unlikeComment(user, commentId);
        return ResponseEntity.ok().build();
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
