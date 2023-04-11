package com.esprit.pidev.services.ForumServices;

import com.esprit.pidev.entities.Forum.Category;
import com.esprit.pidev.entities.Forum.Comment;
import com.esprit.pidev.repository.ForumRepository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class CategoryService implements ICategory {

    CategoryRepository categoryRepository;

    //create
    @Override
    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }


    //update
    @Override
    public Category updateCategory(Category category) {
        return categoryRepository.save(category);
    }

    //retrieve
    @Override
    public Category retrieveCategoryById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    //retirve all category
    @Override
    public List<Category> retrieveAllCategory() {
        return categoryRepository.findAll();
    }

    //delete
    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }


}
