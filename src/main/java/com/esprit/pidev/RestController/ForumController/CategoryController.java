package com.esprit.pidev.RestController.ForumController;

import com.esprit.pidev.entities.Forum.Category;
import com.esprit.pidev.services.ForumServices.ICategory;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class CategoryController {

    ICategory iCategory;


    @PostMapping("/addCategory")
    @ResponseBody
    public Category addCategory(@RequestBody Category category){
        return iCategory.addCategory(category);
    }

    @PutMapping("/updateCategory")
    @ResponseBody
    public Category updateCategory(@RequestBody Category category){
        return iCategory.updateCategory(category);
    }

    @GetMapping("/getCategoryById/{id}")
    @ResponseBody
    public Category retrieveCategoryById(@PathVariable("id") Long id){
        return iCategory.retrieveCategoryById(id);
    }

    @GetMapping("/getAllCategory")
    @ResponseBody
    public List<Category> retrieveAllCategory(){
        return iCategory.retrieveAllCategory();
    }

    @DeleteMapping("/apideleteCategory/{id}")
    public void deleteCategory(@PathVariable("id") Long id){
        iCategory.deleteCategory(id);
    }


}
