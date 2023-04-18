package com.esprit.pidev.services.ForumServices;

import com.esprit.pidev.entities.Forum.Comment;
import com.esprit.pidev.entities.Forum.Post;
import com.esprit.pidev.entities.UserRole.User;
import com.esprit.pidev.repository.ForumRepository.CommentRepository;
import com.esprit.pidev.repository.UserRoleRepository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CommentService implements IComment {

    CommentRepository commentRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
     IPost postService;



    @Autowired
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public Comment addComment(Comment comment, Long postId, Long userId) {
        User retrievedUser = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("User with id " + userId + " not found"));
        comment.setUser(retrievedUser);
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
    public Comment updateComment(Long id, Comment comment) {
        Comment existingComment = commentRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Comment with id " + id + " not found"));

        existingComment.setContent(comment.getContent());
        existingComment.setUser(comment.getUser());

        return commentRepository.save(existingComment);
    }


    @Override
    public Comment retrieveCommentById(Long id) {
        return commentRepository.findById(id).orElse(null);
    }

    @Override
    public List<Comment> retrieveAllComment() {
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        if (!response.isCommitted()) {
            return commentRepository.findAll();
        } else {
            throw new RuntimeException("Could not get response");
        }
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
