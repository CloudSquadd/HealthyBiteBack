package com.esprit.pidev.repository.ForumRepository;

import com.esprit.pidev.entities.Forum.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query("SELECT c FROM Category c LEFT JOIN FETCH c.posts WHERE c.id = :id")
    Category findByIdWithPosts(@Param("id") Long id);

    @Query("SELECT DISTINCT c FROM Category c LEFT JOIN FETCH c.posts")
    List<Category> findAllWithPosts();
}
