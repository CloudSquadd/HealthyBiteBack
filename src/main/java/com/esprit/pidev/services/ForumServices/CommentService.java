package com.esprit.pidev.services.ForumServices;

import com.esprit.pidev.entities.Forum.Comment;
import com.esprit.pidev.repository.ForumRepository.CommentRepository;

import java.util.List;

public class CommentService implements IComment {

    CommentRepository commentRepository;
    @Override
    public Comment addComment(Comment cmt) {
        return commentRepository.save(cmt);
    }

    @Override
    public Comment updateComment(Comment cmt) {
        return commentRepository.save(cmt);
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
}
