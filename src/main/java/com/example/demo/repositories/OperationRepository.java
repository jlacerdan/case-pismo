package com.example.demo.repositories;

import com.example.demo.models.Operation;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OperationRepository extends MongoRepository<Operation, String> {
}
