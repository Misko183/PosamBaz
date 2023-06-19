package com.example.backend.proSecurity.user;

import com.example.backend.Favourite.Favourite;
import com.example.backend.Favourite.FavouriteRepository;
import com.example.backend.Products.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class CurrentUserService implements UserDetailsService {

    private final UserRepository repository;

    @Autowired
    public CurrentUserService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public CurrentUser loadUserByUsername(String username) throws UsernameNotFoundException {

        final UserEntity user = repository.findByUsername(username);
        if (user != null) {
            final CurrentUser currentUser = new CurrentUser();
            currentUser.setId(user.getId());
            currentUser.setUsername(user.getUsername());
            currentUser.setPassword(user.getPassword());
            currentUser.setAuthority(user.getAuthority());
            currentUser.setEnabled(user.isEnabled());
            return currentUser;
        }

        else {
            throw new UsernameNotFoundException("Failed to find user with username: " + username);
        }

    }

    @Autowired
    private FavouriteRepository favouriteRepository;

    @Autowired
    private ProductRepository productRepository;


    public void deleteUser(UserEntity user) {
//        this.favouriteRepository.deleteAll(favouriteRepository.findByUserEntity(user));
//        this.productRepository.deleteAll(productRepository.findByUserEntity(user));
        this.repository.delete(user);

    }
}
