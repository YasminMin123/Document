package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<Document, Long> {
    // Можно добавить кастомные запросы, если это необходимо
}
