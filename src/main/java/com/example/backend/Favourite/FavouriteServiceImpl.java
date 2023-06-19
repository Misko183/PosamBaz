package com.example.backend.Favourite;

import com.example.backend.Image.Image;
import com.example.backend.Image.ImageRepository;
import com.example.backend.Products.Product;
import com.example.backend.Products.ProductRepository;
//import com.example.backend.User.UserServiceImpl;
import com.example.backend.proSecurity.user.CurrentUser;
import com.example.backend.proSecurity.user.UserEntity;
import com.example.backend.proSecurity.user.UserRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class FavouriteServiceImpl implements FavouriteService {

    @Autowired
    private FavouriteRepository favouriteRepository;

    @Autowired
    private ProductRepository productRepository;

//    @Autowired
//    private UserServiceImpl userService;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private UserRepository userRepository;


    @Override
    public void addFavourite(Favourite favourite, @AuthenticationPrincipal CurrentUser currentUser) {
        UserEntity userEntity = userRepository.findByUsername(currentUser.getUsername());

        favourite.setUserEntity(userEntity);
        if (favouriteRepository.findByProductAndUserEntity(favourite.getProduct(), favourite.getUserEntity() ) == null) {
            favouriteRepository.save(favourite);
        }


    }

    public List<Favourite> getFavourites() {
        return favourites;
    }

    public void setFavourites(List<Favourite> favourites) {
        this.favourites = favourites;
    }

    private List<Favourite> favourites;


    public void setIds(List<Long> ids) {
        this.ids = ids;
    }

    private List<Long> ids;


    public ArrayList<Product> getProductList() {
        return productList;
    }

    ArrayList<Product> productList = new ArrayList<>();


    public ArrayList<Image> getFavouriteImages() {
        return favouriteImages;
    }

    ArrayList<Image> favouriteImages = new ArrayList<>();

    public ArrayList<Product> getMeFavourite(@AuthenticationPrincipal CurrentUser currentUser) {

        if (getFavourites() == null) {

            setFavourites(favouriteRepository.findByUserEntity(userRepository.findByUsername(currentUser.getUsername())));

        } else {
            getProductList().clear();
            getFavourites().clear();
            setFavourites(favouriteRepository.findByUserEntity(userRepository.findByUsername(currentUser.getUsername())));
        }

        for (Favourite f : favourites) {
            setIds(Collections.singletonList(f.getProduct().getId()));
            productList.add(productRepository.findById(f.getProduct().getId()).get());
        }

        if (favouriteImages.size() > 0) {
            favouriteImages.clear();
            for (Product product : productList) {
                favouriteImages.add(imageRepository.findById(product.getImage().getId()).get());
                ;
            }
        }
        else {
            for (Product product : productList) {
                favouriteImages.add(imageRepository.findById(product.getImage().getId()).get());
            }
        }

        return productList;
    }

    @Override
    public void removeFavourite(Product product, @AuthenticationPrincipal CurrentUser currentUser) {
        favouriteRepository.delete(favouriteRepository.findByProductAndUserEntity(product, userRepository.findByUsername(currentUser.getUsername())));
    }
}
