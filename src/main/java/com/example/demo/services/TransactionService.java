package com.example.demo.services;

import com.example.demo.exceptions.InvalidOperationTypeException;
import com.example.demo.models.Account;
import com.example.demo.models.Operation;
import com.example.demo.models.Transaction;
import com.example.demo.models.enums.OperationTypeEnum;
import com.example.demo.models.requests.TransactionRequest;
import com.example.demo.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class TransactionService {

    private final TransactionRepository transactionsRepository;
    private final AccountService accountService;
    private final OperationService operationTypeService;

    @Autowired
    public TransactionService(TransactionRepository transactionsRepository, AccountService accountService,
                              OperationService operationTypeService) {
        this.transactionsRepository = transactionsRepository;
        this.accountService = accountService;
        this.operationTypeService = operationTypeService;
    }

    public Transaction createTransaction(TransactionRequest request) {
        Account account = accountService.getAccountById(request.getAccountId());

        Operation operation = operationTypeService.getOperationById(request.getOperationId());

        Transaction transaction = new Transaction();
        transaction.setAccountId(account.getAccountId());
        transaction.setOperationId(operation.getId());
        transaction.setAmount(setTransactionAmountByOperationType(request.getAmount(), operation.getType()));
        transaction.setEventDate(LocalDateTime.now());

        return transactionsRepository.save(transaction);
    }

    public BigDecimal setTransactionAmountByOperationType(BigDecimal amount, OperationTypeEnum type) {
        if (OperationTypeEnum.DEBIT.equals(type)) {
            return amount.negate();
        }
        if (OperationTypeEnum.CREDIT.equals(type)) {
            return amount;
        }
        throw new InvalidOperationTypeException("Operation contains an invalid operation type.");
    }
}
