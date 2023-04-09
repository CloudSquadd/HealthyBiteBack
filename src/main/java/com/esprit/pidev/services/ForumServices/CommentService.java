package com.esprit.pidev.services.ForumServices;

import com.esprit.pidev.entities.Forum.Comment;
import com.esprit.pidev.entities.Forum.Post;
import com.esprit.pidev.repository.ForumRepository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService implements IComment {

    @Autowired
    CommentRepository commentRepository;


    @Autowired
    private IPost postService;


    @Autowired
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }


    @Override
    public Comment addComment(Comment comment, Long postId) {
        Post post = postService.retrievePostById(postId);
        if (post == null) {
            throw new IllegalArgumentException("Post not found");
        }
        if (comment.getContent() == null || comment.getContent().isEmpty()) {
            throw new IllegalArgumentException("Comment content is empty");
        }
        comment.setPost(post);
        return commentRepository.save(comment);
    }


    @Override
    public Comment updateComment(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public Comment retrieveCommentById(Long id) {
        return commentRepository.findById(id).orElse(null);
    }

    @Override
    public List<Comment> retrieveAllComments() {
        return commentRepository.findAll();
    }

    @Override
    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }

    @Override
    public List<Comment> retrieveCommentsByPost(Post post) {
        return commentRepository.findByPost(post);

    }
}
