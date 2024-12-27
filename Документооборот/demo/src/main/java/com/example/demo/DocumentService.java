package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class DocumentService {

    @Autowired
    private DocumentRepository documentRepository;

    private final String uploadDir = "uploads/";

    public Document uploadDocument(MultipartFile file) throws IOException {
        // Получаем имя файла и расширение
        String fileName = file.getOriginalFilename();
        String fileExtension = getFileExtension(fileName);

        // Сохраняем файл в файловую систему
        Path filePath = Paths.get(uploadDir + fileName);
        Files.createDirectories(filePath.getParent());
        file.transferTo(filePath.toFile());

        // Получаем метаданные
        long fileSize = file.getSize();

        // Создаем объект документа
        Document document = new Document();
        document.setName(fileName);
        document.setFileSize(fileSize);
        document.setFileFormat(fileExtension);
        document.setFilePath(filePath.toString());

        // Сохраняем метаданные в базе данных
        return documentRepository.save(document);
    }

    private String getFileExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf(".");
        return (dotIndex == -1) ? "" : fileName.substring(dotIndex + 1).toUpperCase();
    }
}
