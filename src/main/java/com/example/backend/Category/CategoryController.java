package com.example.backend.Category;

import com.example.backend.ImageOfCategory.ImageOfCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ImageOfCategoryRepository imageOfCategoryRepository;

   @PostMapping("/createcategory")
   public void createCategory(@RequestBody Category category){
       Long image = imageOfCategoryRepository.findTopByOrderByIdDesc().get().getId();
       category.setImageOfCategory(imageOfCategoryRepository.findById(image).get());
       categoryRepository.save(category);
   }

   @GetMapping("/getallcategories")
    public List<Category> getAllCategories(){
         return (List<Category>) categoryRepository.findAll();
    }

}
