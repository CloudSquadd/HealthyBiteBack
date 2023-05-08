package com.esprit.pidev.services.ForumServices;

import com.esprit.pidev.entities.Forum.*;
import com.esprit.pidev.entities.UserRole.User;
import com.esprit.pidev.repository.ForumRepository.CommentRepository;
import com.esprit.pidev.repository.ForumRepository.LikeRepository;
import com.esprit.pidev.repository.ForumRepository.PostRepository;
import com.esprit.pidev.repository.UserRoleRepository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class LikeService implements ILike {

        @Autowired
        PostRepository postRepository;
        @Autowired
        UserRepository userRepository;
        @Autowired
        CommentRepository commentRepository;
        @Autowired
        LikeRepository likeRepository;

        @Override
        public Post ToggleLikesP(Long postId, LikeType likeType, long user) {
                Optional<Post> postOptional = postRepository.findById(postId);
                if (!postOptional.isPresent()) {
                        throw new EntityNotFoundException("Post Not Found with ID - " + postId);
                }
                Post post = postOptional.get();
                User userObj = userRepository.findById(user).get(); // fetch User object from repository
                post.setUser(userObj);
                Optional<Like> likeOptional = likeRepository.findByPostAndUser(post, userObj);

                if (likeOptional.isPresent()) { // Existing like found
                        Like existingLike = likeOptional.get();

                        if (existingLike.getLikeType().equals(likeType)) {
                                // Delete existing like
                                likeRepository.delete(existingLike);
                                if (likeType.equals(LikeType.LIKE)) {
                                        post.setLikeCount(post.getLikeCount() - 1);
                                } else {
                                        post.setDislikeCount(post.getDislikeCount() - 1);
                                }
                        } else {
                                existingLike.setLikeType(likeType);
                                likeRepository.save(existingLike);
                                if (likeType.equals(LikeType.LIKE)) {
                                        post.setLikeCount(post.getLikeCount() + 1);
                                        post.setDislikeCount(post.getDislikeCount() - 1);
                                } else {
                                        post.setLikeCount(post.getLikeCount() - 1);
                                        post.setDislikeCount(post.getDislikeCount() + 1);
                                }
                        }
                } else { // No existing like found
                        // Create new like and save it to the database
                        Like newLike = mapToLike(post, likeType, userObj); // pass User object instead of long
                        likeRepository.save(newLike);

                        // Update publication's like count
                        if (likeType.equals(LikeType.LIKE)) {
                                post.setLikeCount(post.getLikeCount() + 1);
                        } else {
                                post.setDislikeCount(post.getDislikeCount() + 1);
                        }
                }

                if (postRepository != null) {
                        postRepository.save(post);
                }
                return post;
        }


        @Override
        public Comment ToggleLikesC(Long commentId, LikeType likeType, long user) {
                Comment comment = commentRepository.findById(commentId)
                        .orElseThrow(() -> new IllegalArgumentException("Publication Not Found with ID - " + commentId));
                User userObj = userRepository.findById(user).get(); // fetch User object from repository
                comment.setUser(userObj);
                Optional<Like> likeOptional = likeRepository.findByCommentAndUser(comment, userObj);
                if (likeOptional.isPresent()) {
                        Like existingLike = likeOptional.get();
                        if (existingLike.getLikeType().equals(likeType)) {
                                likeRepository.delete(existingLike);
                                if (likeType.equals(LikeType.LIKE)) {
                                        comment.setLikeCount(comment.getLikeCount() - 1);
                                } else {
                                        comment.setDislikeCount(comment.getDislikeCount() - 1);
                                }
                        } else {
                                existingLike.setLikeType(likeType);
                                likeRepository.save(existingLike);
                                if (likeType.equals(LikeType.LIKE)) {
                                        comment.setLikeCount(comment.getLikeCount() + 1);
                                        comment.setDislikeCount(comment.getDislikeCount() - 1);
                                } else {
                                        comment.setLikeCount(comment.getLikeCount() - 1);
                                        comment.setDislikeCount(comment.getDislikeCount() + 1);
                                }
                        }
                } else {
                        Like newLike = mapToLikeC(comment, likeType, userObj);
                        likeRepository.save(newLike);
                        if (likeType.equals(LikeType.LIKE)) {
                                comment.setLikeCount(comment.getLikeCount() + 1);
                        } else {
                                comment.setDislikeCount(comment.getDislikeCount() + 1);
                        }
                }

                commentRepository.save(comment);
                return comment;
        }


        private Like mapToLike(Post post, LikeType likeType, User user) {
                return Like.builder()
                        .likeType(likeType)
                        .post(post)
                        .user(user)
                        .build();
        }

        private Like mapToLikeC(Comment comment, LikeType likeType, User user) {
                return Like.builder()
                        .likeType(likeType)
                        .comment(comment)
                        .user(user)
                        .build();
        }



}