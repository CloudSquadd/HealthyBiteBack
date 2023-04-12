package com.esprit.pidev.repository.ForumRepository;

import com.esprit.pidev.entities.Forum.Dislike;
import com.esprit.pidev.entities.Forum.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DislikeRepository extends JpaRepository<Dislike,Long> {
    List<Dislike> findByPost(Post post);

}
