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



}