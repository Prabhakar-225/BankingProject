package com.banking.s3Aws.controller;

import com.banking.s3Aws.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/files")
public class StorageController {

    @Autowired
    private StorageService service;

    @PostMapping("/uploadFile")
    public ResponseEntity<String> uploadFile(@RequestParam(value = "file") MultipartFile file) {
        return new ResponseEntity<>(service.uploadFile(file), HttpStatus.OK);
    }

    @GetMapping("/downloadFile/{fileName}")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable(value = "fileName") String filename) {
        byte[] data = service.downloadFile(filename);
        ByteArrayResource resource = new ByteArrayResource(data);
        return ResponseEntity.ok()
                .contentLength(data.length)
                .header("content-type", "application/octest-stream")
                .header("content-disposition", "attachment; filename=\"" + filename + "\"")
                .body(resource);
    }


    @DeleteMapping("/deleteFile/{fileName}")
    public ResponseEntity<String> deleteFile(@PathVariable(value = "fileName") String fileName){
        return new ResponseEntity<>(service.deleteFile(fileName), HttpStatus.OK);
    }


}
