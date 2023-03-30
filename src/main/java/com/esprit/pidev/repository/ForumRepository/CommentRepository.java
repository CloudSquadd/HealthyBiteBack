package com.esprit.pidev.repository.ForumRepository;

import com.esprit.pidev.entities.Forum.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Long> {
}
