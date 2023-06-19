package com.example.backend.Products;

//import com.example.backend.User.User;
import com.example.backend.Category.Category;
import com.example.backend.proSecurity.user.CurrentUser;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.util.List;

public interface ProductService {

    void addProduct(Product product, @AuthenticationPrincipal CurrentUser currentUser);

    void deleteProduct(Product product);

    void updateProduct(Product product);

    void getByCategory(String category);

    List<Product> showOnlyUsersProducts(@AuthenticationPrincipal CurrentUser currentUser);

    void editProduct(Product product);

    void returnProductsByCategory(Category category);
}
