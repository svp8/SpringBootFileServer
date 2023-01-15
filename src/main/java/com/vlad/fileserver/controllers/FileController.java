package com.vlad.fileserver.controllers;

import com.vlad.fileserver.models.File;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FileController {
    @GetMapping("/file")
    public File getFile(){
        File file=new File();
        file.setName("test");
        file.setId(1);
        return file;
    }
}
