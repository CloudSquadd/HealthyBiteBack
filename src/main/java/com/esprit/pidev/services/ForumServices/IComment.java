package com.esprit.pidev.services.ForumServices;

import com.esprit.pidev.entities.Forum.Comment;
import com.esprit.pidev.entities.Forum.Post;

import java.util.List;

public interface IComment {
    Comment addComment(Comment comment);
    Comment updateComment(Comment comment);
    Comment retrieveCommentById(Long id);
    List<Comment> retrieveAllComments();
    void deleteComment(Long id);
    List<Comment> retrieveCommentsByPost(Post post);

}
