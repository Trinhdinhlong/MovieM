package com.mocktest.controllers;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
@CrossOrigin("*")
@RestController
public class ImageController {

    private static final String IMAGE_PATH = "./images/";
    @PostMapping("/images")
    @ResponseBody
    public String uploadImages(@ModelAttribute MultipartFile imageFile) throws IOException {
            String imageFileName = null;
            if(imageFile != null) {
                imageFileName = imageFile.getOriginalFilename();
                Path path  = Paths.get(IMAGE_PATH, imageFileName);
                Files.copy(imageFile.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            }
            return imageFileName;
        }
    @GetMapping("/images/{imageName}")
    public ResponseEntity<ByteArrayResource> getImage(@PathVariable("imageName") String imageName)
            throws IOException{
        ByteArrayResource byteArrayResource = getImageByName(imageName);
        return ResponseEntity.ok()
                .contentLength(byteArrayResource.contentLength())
                .contentType(MediaType.IMAGE_PNG)
                .body(byteArrayResource);
    }

    public ByteArrayResource getImageByName(String imageName) throws IOException {
        Path path  = Paths.get(IMAGE_PATH, imageName);
        byte[] readAllBytes = Files.readAllBytes(path);
        return new ByteArrayResource(readAllBytes);
    }
}
