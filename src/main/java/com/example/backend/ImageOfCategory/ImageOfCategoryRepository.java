package com.example.backend.ImageOfCategory;

import com.example.backend.Image.Image;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImageOfCategoryRepository extends CrudRepository<ImageOfCategory, Long> {

    Optional<ImageOfCategory> findTopByOrderByIdDesc();
}
