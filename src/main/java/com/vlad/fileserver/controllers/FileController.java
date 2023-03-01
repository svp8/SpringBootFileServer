package com.vlad.fileserver.controllers;

import com.vlad.fileserver.models.File;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;


@RestController
public class FileController {
    @GetMapping("/file")
    public File getFile(){
        File file=new File();
        file.setName("test");
        file.setId(1);
        return file;
    }
    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file){
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(new java.io.File(".//files//"+file.getOriginalFilename())));
                stream.write(bytes);
                stream.close();
                return "ok";
            } catch (Exception e) {
                return "Вам не удалось загрузить " +" => " + e.getMessage();
            }
        } else {
            return "Вам не удалось загрузить "  + " потому что файл пустой.";
        }
    }
    @GetMapping("/download/{filename}")
    public ResponseEntity<?> downloadFile(@PathVariable String filename){
        java.io.File downloadFile = new java.io.File(".//files//"+filename);
        InputStreamResource resource = null;
        try {
            resource = new InputStreamResource(new FileInputStream(downloadFile));
        } catch (FileNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("File not found");
        }
        HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + downloadFile.getName());
        header.add("Cache-Control", "no-cache, no-store, must-revalidate");
        header.add("Pragma", "no-cache");
        header.add("Expires", "0");
        return ResponseEntity.ok()
                .headers(header)
                .contentLength(downloadFile.length())
//                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .contentType(MediaType.IMAGE_JPEG)
                .body(resource);

    }
}
