package com.example.backend.ImageOfCategory;

import com.example.backend.Image.Image;
import com.example.backend.Image.ImageUploadResponse;
import com.example.backend.Image.ImageUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RestController
public class ImageOfCategoryController {

    @Autowired
    private ImageOfCategoryRepository imageOfCategoryRepository;

    @Primary
    @PostMapping("/upload/categoryimage")
    public ResponseEntity<ImageUploadResponse> uplaodImage(@RequestParam("image") MultipartFile file)
            throws IOException {

        imageOfCategoryRepository.save(ImageOfCategory.builder()
                .type(file.getContentType())
                .image(ImageUtility.compressImage(file.getBytes())).build());


        return ResponseEntity.status(HttpStatus.OK)
                .body(new ImageUploadResponse("Image uploaded successfully: " +
                        file.getOriginalFilename()));

    }

    @GetMapping(path = {"/get/categoryimage/info/{id}"})
    public ImageOfCategory getImageDetails(@PathVariable("id") Long id) throws IOException {

        final Optional<ImageOfCategory> dbImage = imageOfCategoryRepository.findById(id);

        return ImageOfCategory.builder()
                .id(dbImage.get().getId())
                .type(dbImage.get().getType())
                .image(ImageUtility.decompressImage(dbImage.get().getImage())).build();
    }


}
