package com.example.backend.ImageAnother;

import com.example.backend.Image.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ImageAnotherRepository extends JpaRepository<ImageAnother, Long> {

    List<ImageAnother> findAllByImageMain(Image imageMain);
}
