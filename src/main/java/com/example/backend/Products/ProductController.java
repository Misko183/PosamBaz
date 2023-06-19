package com.example.backend.Products;

//import com.example.backend.User.User;
import com.example.backend.Category.Category;
import com.example.backend.proSecurity.user.CurrentUser;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@NoArgsConstructor
public class ProductController {

    @Autowired
    private ProductServiceImpl productService;

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/advertisement")
    public List<Product> getAllProduct() {
        return (List<Product>) productRepository.findAll();
    }

    @PostMapping("/advertisement")
    public void addProduct(@RequestBody Product product, @AuthenticationPrincipal CurrentUser currentUser) {
        productService.addProduct(product, currentUser);
    }

    @PostMapping("/updateproduct")
    void updateProduct(@RequestBody Product product) {
        productService.updateProduct(product);
    }

    @PostMapping("/deleteproduct")
    void deleteProduct(@RequestBody Product product) {
        productService.deleteProduct(product);
    }

    @PostMapping("/category")
    public void showCategory(@RequestBody String category){
           productService.getByCategory(category);
    }

    @GetMapping("/advertisement/category")
    public List<Product> showOnlyOneCategory(){
        return productService.getCurrentCategory();
    }

    @GetMapping("/myproducts")
    public List<Product> showOnlyMe(@AuthenticationPrincipal CurrentUser currentUser){
        return productService.showOnlyUsersProducts(currentUser);
    }

    @PostMapping("/editproduct")
    void editProduct(@RequestBody Product product) {
        productService.editProduct(product);
    }

    @GetMapping("/getcategoryinformation")
    List<Product> returnProductsByCategory(){
       return productService.getCurrentCategories();
    }

    @PostMapping("/postcategoryinformation")
    void postProductsByCategory(@RequestBody Category category){
         productService.returnProductsByCategory(category);
    }
}
