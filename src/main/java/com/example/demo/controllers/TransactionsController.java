package com.example.demo.controllers;

import com.example.demo.exceptions.RequestNotValidException;
import com.example.demo.models.Transaction;
import com.example.demo.models.requests.TransactionRequest;
import com.example.demo.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/transactions")
public class TransactionsController {

    private final TransactionService transactionsService;

    @Autowired
    public TransactionsController(TransactionService transactionsService) {
        this.transactionsService = transactionsService;
    }

    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@RequestBody TransactionRequest transactionRequest) {
        if (!isTransactionRequestValid(transactionRequest)) {
            throw new RequestNotValidException("Transaction request is invalid");
        }
        return new ResponseEntity<>(transactionsService.createTransaction(transactionRequest), HttpStatus.CREATED);
    }

    private boolean isTransactionRequestValid(TransactionRequest request) {
        return StringUtils.hasText(request.getAccountId()) && StringUtils.hasText(request.getOperationId())
                && isPositiveAmount(request.getAmount());
    }

    private boolean isPositiveAmount(BigDecimal amount) {
        return amount != null && amount.compareTo(BigDecimal.ZERO) > 0;
    }
}
