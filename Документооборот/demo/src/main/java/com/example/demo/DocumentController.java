package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@RestController
@RequestMapping("/documents")
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    @PostMapping("/upload")
    public String uploadDocument(@RequestParam("file") MultipartFile file) {
        try {
            Document uploadedDocument = documentService.uploadDocument(file);
            return "Document uploaded successfully with ID: " + uploadedDocument.getId();
        } catch (IOException e) {
            return "Failed to upload document: " + e.getMessage();
        }
    }
}
