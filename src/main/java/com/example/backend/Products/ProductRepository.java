package com.example.backend.Products;

//import com.example.backend.User.User;
import com.example.backend.Category.Category;
import com.example.backend.proSecurity.user.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

    List<Product> findByCategory(String vehicles);

    List<Product> findByUserEntityUsername(String username);

    List<Product> findByUserEntity(UserEntity user);

   // Optional<Product> findById(Long id);

    Optional<Product> findById(Long id);

    void deleteByUserEntityId(long id);

    List<Product> findAllByKindOfCategory(Category category);
}
