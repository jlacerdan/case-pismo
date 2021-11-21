package com.example.demo.services;

import com.example.demo.exceptions.OperationNotFoundException;
import com.example.demo.models.Operation;
import com.example.demo.repositories.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OperationService {
    private final OperationRepository operationRepository;

    @Autowired
    public OperationService(OperationRepository operationRepository) {
        this.operationRepository = operationRepository;
    }

    public Operation getOperationById(String operationTypeId) {
        return operationRepository.findById(operationTypeId).orElseThrow(() -> new OperationNotFoundException(operationTypeId));
    }
}
