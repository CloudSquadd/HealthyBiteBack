package com.esprit.pidev.repository.ForumRepository;

import com.esprit.pidev.entities.Forum.Like;
import com.esprit.pidev.entities.Forum.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikeRepository extends JpaRepository<Like,Long> {
    List<Like> findByPost(Post post);

}
