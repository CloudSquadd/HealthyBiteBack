package com.esprit.pidev.repository.ForumRepository;

import com.esprit.pidev.entities.Forum.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
