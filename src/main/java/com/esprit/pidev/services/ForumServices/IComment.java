package com.esprit.pidev.services.ForumServices;

import com.esprit.pidev.entities.Forum.Comment;
import com.esprit.pidev.entities.Forum.Post;

import java.util.List;

public interface IComment {

    Comment addComment(Comment comment,Long postId, Long userId);
    Comment updateComment(Long id,Comment comment);
    Comment retrieveCommentById(Long id);
    List<Comment> retrieveAllComment();
    void deleteComment(Long id);
    List<Comment> retrieveCommentsByPost(Post post);

}
