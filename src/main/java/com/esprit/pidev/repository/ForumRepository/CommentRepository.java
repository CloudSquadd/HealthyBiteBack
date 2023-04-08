package com.esprit.pidev.repository.ForumRepository;

import com.esprit.pidev.entities.Forum.Comment;
import com.esprit.pidev.entities.Forum.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findByPost(Post post);
}
