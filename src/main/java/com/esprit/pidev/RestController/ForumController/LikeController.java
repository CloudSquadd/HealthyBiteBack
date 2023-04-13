package com.esprit.pidev.RestController.ForumController;


import com.esprit.pidev.entities.UserRole.User;
import com.esprit.pidev.services.ForumServices.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LikeController {

    @Autowired
    private LikeService likeService;

    @PostMapping("/posts/{postId}/like")
    public ResponseEntity<?> likePost(@AuthenticationPrincipal User user, @PathVariable Long postId) {
        likeService.likePost(user, postId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/posts/{postId}/unlike")
    public ResponseEntity<?> unlikePost(@AuthenticationPrincipal User user, @PathVariable Long postId) {
        likeService.unlikePost(user, postId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/comments/{commentId}/like")
    public ResponseEntity<?> likeComment(@AuthenticationPrincipal User user, @PathVariable Long commentId) {
        likeService.likeComment(user, commentId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/comments/{commentId}/unlike")
    public ResponseEntity<?> unlikeComment(@AuthenticationPrincipal User user, @PathVariable Long commentId) {
        likeService.unlikeComment(user, commentId);
        return ResponseEntity.ok().build();
    }
}
