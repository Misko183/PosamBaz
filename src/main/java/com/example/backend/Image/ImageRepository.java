package com.example.backend.Image;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image, Long> {

	Optional<Image> findById(Long id);

	Optional<Image>  findTopByOrderByIdDesc();

}


