package com.esprit.pidev.services.ForumServices;

import com.esprit.pidev.entities.Forum.Comment;
import com.esprit.pidev.entities.Forum.LikeEntity;
import com.esprit.pidev.entities.Forum.Post;
import com.esprit.pidev.entities.User;
import com.esprit.pidev.repository.ForumRepository.CommentRepository;
import com.esprit.pidev.repository.ForumRepository.LikeRepository;
import com.esprit.pidev.repository.ForumRepository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class LikeService implements ILike {

        @Autowired
        private LikeRepository likeRepository;

        @Autowired
        private PostRepository postRepository;

        @Autowired
        private CommentRepository commentRepository;

        @Override
        public void likePost(User user, Long postId) {
                Post post = postRepository.findById(postId)
                        .orElseThrow(() -> new EntityNotFoundException("Post not found"));

                LikeEntity like = likeRepository.findByUserAndPost(user, post)
                        .orElseGet(() -> new LikeEntity(user, post));

                like.setLiked(true);
                likeRepository.save(like);
        }

        @Override
        public void unlikePost(User user, Long postId) {
                Post post = postRepository.findById(postId)
                        .orElseThrow(() -> new EntityNotFoundException("Post not found"));

                LikeEntity like = likeRepository.findByUserAndPost(user, post)
                        .orElseThrow(() -> new EntityNotFoundException("Like not found"));

                like.setLiked(false);
                likeRepository.save(like);
        }

        @Override
        public void likeComment(User user, Long commentId) {
                Comment comment = commentRepository.findById(commentId)
                        .orElseThrow(() -> new EntityNotFoundException("Comment not found"));

                LikeEntity like = likeRepository.findByUserAndComment(user, comment)
                        .orElseGet(() -> new LikeEntity(user, comment));

                like.setLiked(true);
                likeRepository.save(like);
        }

        @Override
        public void unlikeComment(User user, Long commentId) {
                Comment comment = commentRepository.findById(commentId)
                        .orElseThrow(() -> new EntityNotFoundException("Comment not found"));

                LikeEntity like = likeRepository.findByUserAndComment(user, comment)
                        .orElseThrow(() -> new EntityNotFoundException("Like not found"));

                like.setLiked(false);
                likeRepository.save(like);
        }
}
