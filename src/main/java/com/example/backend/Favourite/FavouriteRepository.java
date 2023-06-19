package com.example.backend.Favourite;

import com.example.backend.Products.Product;
//import com.example.backend.User.User;
import com.example.backend.proSecurity.user.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavouriteRepository extends CrudRepository<Favourite, Long> {

    List<Favourite> findByUserEntity(UserEntity user);

    void deleteAllByProductId(long id);

    Iterable<? extends Favourite> findByProduct(Product product);

    Favourite findByProductAndUserEntity(Product product, UserEntity user);

    List<Favourite> findAllByProduct(Product product);
}
