package com.esprit.pidev.RestController.ForumController;


import com.esprit.pidev.entities.Forum.Comment;
import com.esprit.pidev.entities.Forum.LikeEntity;
import com.esprit.pidev.entities.Forum.LikeType;
import com.esprit.pidev.entities.Forum.Post;
import com.esprit.pidev.entities.UserRole.User;
import com.esprit.pidev.repository.ForumRepository.CommentRepository;
import com.esprit.pidev.repository.ForumRepository.LikeRepository;
import com.esprit.pidev.repository.ForumRepository.PostRepository;
import com.esprit.pidev.repository.UserRoleRepository.UserRepository;
import com.esprit.pidev.services.ForumServices.ILike;
import com.esprit.pidev.services.ForumServices.LikeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")
@AllArgsConstructor
@RequestMapping("/api/test")
@RestController
public class LikeController {
    LikeService likeService;
    UserRepository userRepository;


    @PutMapping("/posts/{postId}")
    public ResponseEntity<Post> toggleLikeOnPost(@PathVariable Long postId, @RequestParam LikeType likeType) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Long userId = getUserIdFromAuth(auth);
        Post post = likeService.ToggleLikesP(postId, likeType, userId);
        return ResponseEntity.ok(post);
    }

    @PutMapping("/comments/{commentId}")
    public ResponseEntity<Comment> toggleLikeOnComment(@PathVariable Long commentId, @RequestParam LikeType likeType) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Long userId = getUserIdFromAuth(auth);
        Comment comment = likeService.ToggleLikesC(commentId, likeType, userId);
        return ResponseEntity.ok(comment);
    }

    private Long getUserIdFromAuth(Authentication auth) {
        String username = auth.getName();
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username))
                .getId();
    }
/*
    CommentRepository commentRepository;
    LikeRepository likeRepository;
    PostRepository postRepository;
    ILike ilike;


    @PostMapping("/posts/{postId}/like")
    public ResponseEntity<?> likePost(@AuthenticationPrincipal User userId, @PathVariable Long postId) {
        if (likeService.isPostLikedByUser( postId)) {
            return ResponseEntity.badRequest().body("Post already liked by user");
        }
        likeService.likePost(userId, postId);
        return ResponseEntity.ok().body("you liked the post");
    }

    @PostMapping("/posts/{postId}/unlike")
    public ResponseEntity<?> unlikePost( @PathVariable Long postId) {
        if (!likeService.isPostLikedByUser( postId)) {
            return ResponseEntity.badRequest().body("Post not liked by user");
        }
        likeService.unlikePost( postId);
        return ResponseEntity.ok().body("you unliked the post");
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

    public boolean isPostLikedByUser(User user, Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("Post not found"));
        Optional<LikeEntity> like = likeRepository.findByUserAndPost(user, post);
        return like.map(LikeEntity::isLiked).orElse(false);
    }

    public boolean isCommentLikedByUser(User user, Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException("Comment not found"));
        Optional<LikeEntity> like = likeRepository.findByUserAndComment(user, comment);
        return like.map(LikeEntity::isLiked).orElse(false);
    }
*/


}