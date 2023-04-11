package com.esprit.pidev.services.ForumServices;

import com.esprit.pidev.entities.Forum.Category;

import java.util.List;

public interface ICategory {
    Category addCategory(Category category);
    Category updateCategory(Category category);
    Category retrieveCategoryById(Long id);
    List<Category> retrieveAllCategory();
    void deleteCategory(Long id);
}
