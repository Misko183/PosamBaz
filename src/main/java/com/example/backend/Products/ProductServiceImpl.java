package com.example.backend.Products;

import com.example.backend.Category.Category;
import com.example.backend.Compare.CompareProducts;
import com.example.backend.Compare.CompareProductsRepository;
import com.example.backend.Favourite.Favourite;
import com.example.backend.Favourite.FavouriteRepository;
import com.example.backend.Favourite.FavouriteService;
import com.example.backend.Favourite.FavouriteServiceImpl;
import com.example.backend.Image.Image;
import com.example.backend.Image.ImageController;
import com.example.backend.Image.ImageRepository;
//import com.example.backend.User.UserServiceImpl;
import com.example.backend.ImageAnother.ImageAnother;
import com.example.backend.ImageAnother.ImageAnotherRepository;
import com.example.backend.proSecurity.user.CurrentUser;
import com.example.backend.proSecurity.user.UserEntity;
import com.example.backend.proSecurity.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;


@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private ImageController imageController;

    @Autowired
    private ImageAnotherRepository imageAnotherRepository;

    @Autowired
    private FavouriteServiceImpl favouriteService;

    @Autowired
    private FavouriteRepository favouriteRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CompareProductsRepository compareProductsRepository;

    @Override
    public void addProduct(Product product, @AuthenticationPrincipal CurrentUser currentUser) {

        Long image = imageRepository.findTopByOrderByIdDesc().get().getId();
        product.setImage(imageRepository.findById(image).get());
        product.setUserEntity(userRepository.findByUsername(currentUser.getUsername()));
        product.setPrice(product.getPrice().toLowerCase(Locale.ROOT));
        if(product.getPrice().contains("€") || product.getPrice().contains("$") || product.getPrice().contains("£") || product.getPrice().contains("dohodou")){
            productRepository.save(product);
        }
        else{
            product.setPrice(product.getPrice()+ "€");
            productRepository.save(product);
        }


    }

    @Override
    public void deleteProduct(Product product) {
        favouriteRepository.deleteAll(favouriteRepository.findByProduct(product));

        List<CompareProducts> compareProducts = (List<CompareProducts>) compareProductsRepository.findAll();
        for (CompareProducts compareProduct : compareProducts) {
            if(compareProduct.getProduct1().getId() == product.getId()){
                compareProduct.setProduct1(compareProduct.getProduct2());
                compareProduct.setProduct2(null);
                compareProductsRepository.save(compareProduct);
            }
            else if(compareProduct.getProduct2().getId() == product.getId()){
                compareProduct.setProduct2(null);
                compareProductsRepository.save(compareProduct);
            }
        }

//        imageAnotherRepository.deleteAll(imageAnotherRepository.findAllByImageMain(product.getImage()));
//        imageRepository.deleteById(productRepository.findById(product.getId()).get().getImage().getId());
        productRepository.deleteById(product.getId());
    }

    @Override
    public void updateProduct(Product product) {
       Product myProduct = productRepository.findById(product.getId()).get();
        myProduct.setCountClicksOnProduct(myProduct.getCountClicksOnProduct() + 1);
        productRepository.save(myProduct);
    }

    @Override
    public void editProduct(Product product) {
        product.setPrice(product.getPrice().toLowerCase(Locale.ROOT));
        if(product.getPrice().contains("€") || product.getPrice().contains("$") || product.getPrice().contains("£") || product.getPrice().contains("dohodou")){
            productRepository.save(product);
        }
        else{
            product.setPrice(product.getPrice()+ "€");
            productRepository.save(product);
        }

    }


    public List<Product> getCurrentCategory() {
        return currentCategory;
    }

    public void setCurrentCategory(List<Product> currentCategory) {
        this.currentCategory = currentCategory;
    }

    List<Product> currentCategory;


    public List<Image> getCategoryImages() {
        return categoryImages;
    }

    List<Image> categoryImages = new ArrayList<>();

    @Override
    public void getByCategory(String category) {
       setCurrentCategory(productRepository.findByCategory(category));
       //find all images for products in current category

        if (getCategoryImages().size() > 0) {
            getCategoryImages().clear();
            for (Product product : currentCategory) {
                categoryImages.add(imageRepository.findById(product.getImage().getId()).get());
                ;
            }
        }
        else{
            for(Product product : currentCategory){
              categoryImages.add(imageRepository.findById(product.getImage().getId()).get());
         }
            }
    }



    public void setOnlyUsersProducts(List<Product> onlyUsersProducts) {
        this.onlyUsersProducts = onlyUsersProducts;
    }

    List<Product> onlyUsersProducts = new ArrayList<>();


    public List<Image> getUsersImages() {
        return usersImages;
    }

    List<Image> usersImages = new ArrayList<>();

    @Override
    public List<Product> showOnlyUsersProducts(@AuthenticationPrincipal CurrentUser currentUser) {
        setOnlyUsersProducts(productRepository.findByUserEntityUsername(currentUser.getUsername()));
        if (getUsersImages().size() > 0) {
            getUsersImages().clear();
            for (Product product : onlyUsersProducts) {
                usersImages.add(imageRepository.findById(product.getImage().getId()).get());
            }
        }
        else{
            for(Product product : onlyUsersProducts){
                usersImages.add(imageRepository.findById(product.getImage().getId()).get());
            }
        }

        return productRepository.findByUserEntityUsername(currentUser.getUsername());
    }


    public List<Product> getCurrentCategories() {
        return currentCategories;
    }

    public void setCurrentCategories(List<Product> currentCategories) {
        this.currentCategories = currentCategories;
    }

    List<Product> currentCategories;

    @Override
    public void returnProductsByCategory(Category category){
        setCurrentCategories(productRepository.findAllByKindOfCategory(category));
    }


}
