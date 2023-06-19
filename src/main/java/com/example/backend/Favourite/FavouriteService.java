package com.example.backend.Favourite;

import com.example.backend.Products.Product;
import com.example.backend.proSecurity.user.CurrentUser;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

public interface FavouriteService {
     void addFavourite(Favourite favourite, @AuthenticationPrincipal CurrentUser currentUser);

     void removeFavourite(Product product, @AuthenticationPrincipal CurrentUser currentUser);

}
