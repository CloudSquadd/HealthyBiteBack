package com.esprit.pidev.repository.ForumRepository;

import com.esprit.pidev.entities.Forum.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {
}
