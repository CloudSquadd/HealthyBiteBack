package com.esprit.pidev.services.ForumServices;

import com.esprit.pidev.entities.Forum.*;
import com.esprit.pidev.entities.UserRole.User;
import com.esprit.pidev.repository.ForumRepository.CommentRepository;
import com.esprit.pidev.repository.ForumRepository.LikeRepository;
import com.esprit.pidev.repository.ForumRepository.PostRepository;
import com.esprit.pidev.repository.UserRoleRepository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
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
        public Post ToggleLikesP(Long postId, LikeType likeType, Long userId) {
                Post post = postRepository.findById(postId)
                        .orElseThrow(() -> new IllegalArgumentException("Publication Not Found with ID - " + postId));
                User user = userRepository.findById(userId)
                        .orElseThrow(() -> new IllegalArgumentException("User Not Found with ID - " + userId));
                Optional<Like> likeOptional = likeRepository.findByPostAndUser(post, user);

                if (likeOptional.isPresent()) {
                        Like existingLike = likeOptional.get();
                        if (existingLike.getLikeType().equals(likeType)) {
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
                } else {
                        Like newLike = mapToLike(post, likeType, user);
                        likeRepository.save(newLike);
                        if (likeType.equals(LikeType.LIKE)) {
                                post.setLikeCount(post.getLikeCount() + 1);
                        } else {
                                post.setDislikeCount(post.getDislikeCount() + 1);
                        }
                }

                postRepository.save(post);
                return post;
        }

        @Override
        public Comment ToggleLikesC(Long commentId, LikeType likeType, Long userId) {
                Comment comment = commentRepository.findById(commentId)
                        .orElseThrow(() -> new IllegalArgumentException("Publication Not Found with ID - " + commentId));
                User user = userRepository.findById(userId)
                        .orElseThrow(() -> new IllegalArgumentException("User Not Found with ID - " + userId));
                Optional<Like> likeOptional = likeRepository.findByCommentAndUser(comment, user);
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
                        Like newLike = mapToLikeC(comment, likeType, user);
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




  /*  @Override
    public Publication ToggleLikes(Integer idPub, Long idUser) {
        Publication publication = publicationRepository.findById(idPub).orElse(null);
        User user = userRepository.findById(idUser).orElse(null);
        if (publication != null && user != null) {
            List<Like> Likes = likeRepository.findAll().stream()
                    .filter(x -> x.getPublication().getIdPub() == idPub)
                    .filter(x -> x.getUser().getId() == idUser)
                    .collect(Collectors.toList());
            if (Likes.size() > 0) {
                likeRepository.deleteById(Likes.get(0).getIdlike());
            }
            else {
                Like like = new Like();
                like.setUser(user);
                like.setPublication(publication);
                likeRepository.save(like);
            }
             }
            return publication;
        }*/

