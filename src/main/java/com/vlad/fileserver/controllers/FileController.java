package com.vlad.fileserver.controllers;


import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;


@RestController
public class FileController {

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file){
        if (!file.isEmpty()) {
            try {
                File f = new File(".//files//"+file.getOriginalFilename());
                if(f.exists() && f.isFile()) {
                    return ResponseEntity.badRequest().body("Файл уже существует");
                }
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(".//files//"+file.getOriginalFilename()));
                stream.write(bytes);
                stream.close();
                return ResponseEntity.ok("Файл загружен");
            } catch (Exception e) {
                return ResponseEntity.badRequest().body("Вам не удалось загрузить " +" => " + e.getMessage());
            }
        } else {
            return ResponseEntity.unprocessableEntity().body("Вам не удалось загрузить "  + " потому что файл пустой.");
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
//                .contentType(MediaType.IMAGE_JPEG)
                .body(resource);

    }
}
