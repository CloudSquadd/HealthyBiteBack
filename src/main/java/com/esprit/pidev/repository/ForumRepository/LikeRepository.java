package com.esprit.pidev.repository.ForumRepository;

import com.esprit.pidev.entities.Forum.LikeEntity;
import com.esprit.pidev.entities.Forum.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikeRepository extends JpaRepository<LikeEntity,Long> {
    List<LikeEntity> findByPost(Post post);

}
