package com.example.demo.repositories;

import com.example.demo.models.Tool;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToolRepository extends MongoRepository<Tool, Long> {
}
