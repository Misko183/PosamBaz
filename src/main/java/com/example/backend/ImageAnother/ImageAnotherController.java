package com.example.backend.ImageAnother;

import com.example.backend.Image.Image;
import com.example.backend.Image.ImageRepository;
import com.example.backend.Image.ImageUploadResponse;
import com.example.backend.Image.ImageUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

@RestController
public class ImageAnotherController {

    @Autowired
    private ImageAnotherRepository imageAnotherRepository;

    @Autowired
    private ImageRepository imageRepository;

    @PostMapping("/upload/anotherImage")
    public void uploadImage(@RequestParam("image") MultipartFile[] files) throws IOException {
        System.out.println(Arrays.toString(files));

        if (files.length != 0) {
        for (MultipartFile file : files) {
            imageAnotherRepository.save(ImageAnother.builder()
                    .type(file.getContentType())
                    .imageMain(imageRepository.findTopByOrderByIdDesc().get())
                    .image(ImageUtility.compressImage(file.getBytes())).build());


        }
    }
    }
}
