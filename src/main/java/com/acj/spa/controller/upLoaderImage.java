package com.acj.spa.controller;

import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class upLoaderImage {


    @PostMapping ("/upload")
    public boolean pictureupload(@RequestParam("file") MultipartFile file) {

        System.out.println(file.getName());
        System.out.println(file.getOriginalFilename());
        System.out.println(file.getSize());

        try {
            Path downloadedFile = Paths.get(file.getOriginalFilename());
            Files.deleteIfExists(downloadedFile);

            Files.copy(file.getInputStream(), downloadedFile);

            return true;
        }
        catch (IOException e) {
            LoggerFactory.getLogger(this.getClass()).error("picture upload", e);
            return false;
        }

    }
}