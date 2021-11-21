package com.example.demo.mass;

import com.example.demo.models.Account;
import com.example.demo.models.Operation;
import com.example.demo.models.Transaction;
import com.example.demo.models.enums.OperationTypeEnum;
import com.example.demo.models.requests.AccountRequest;
import com.example.demo.models.requests.TransactionRequest;

import java.math.BigDecimal;
import java.util.Optional;

public class DataMass {

    public static final String ACCOUNT_ID = "ACCOUNT_ID_TEST";
    public static final String DOCUMENT_NUMBER = "1234567890";

    public static AccountRequest getAccountRequest() {
        AccountRequest accountRequest = new AccountRequest();
        accountRequest.setDocumentNumber(DOCUMENT_NUMBER);
        return accountRequest;
    }

    public static Optional<Account> getAccount() {
        Account account = new Account();
        account.setAccountId(ACCOUNT_ID);
        account.setDocumentNumber(DOCUMENT_NUMBER);
        return Optional.of(account);
    }

    public static Operation getOperationWithTypeDebit() {
        Operation operation = new Operation();
        operation.setId("1");
        operation.setType(OperationTypeEnum.DEBIT);
        operation.setDescription("DEBIT");
        return operation;
    }

    public static Operation getOperationWithTypeCredit() {
        Operation operation = new Operation();
        operation.setId("2");
        operation.setType(OperationTypeEnum.CREDIT);
        operation.setDescription("CREDIT");
        return operation;
    }

    public static Transaction getTransaction() {
        Transaction transaction = new Transaction();
        transaction.setAmount(BigDecimal.TEN);
        transaction.setAccountId(ACCOUNT_ID);
        transaction.setOperationId("1");
        return transaction;
    }

    public static TransactionRequest getTransactionRequest() {
        TransactionRequest request = new TransactionRequest();
        request.setAccountId(ACCOUNT_ID);
        request.setAmount(BigDecimal.TEN);
        request.setOperationId("1");
        return request;
    }

    public static TransactionRequest getTransactionRequestWithoutAccountId() {
        TransactionRequest request = new TransactionRequest();
        request.setAmount(BigDecimal.TEN);
        request.setOperationId("1");
        return request;
    }

    public static TransactionRequest getTransactionRequestWithoutOperationId() {
        TransactionRequest request = new TransactionRequest();
        request.setAccountId(ACCOUNT_ID);
        request.setAmount(BigDecimal.TEN);
        return request;
    }

    public static TransactionRequest getTransactionRequestWithNegativeAmount() {
        TransactionRequest request = new TransactionRequest();
        request.setAccountId(ACCOUNT_ID);
        request.setAmount(BigDecimal.TEN.negate());
        request.setOperationId("1");
        return request;
    }

    public static TransactionRequest getTransactionRequestWithAmountZero() {
        TransactionRequest request = new TransactionRequest();
        request.setAccountId(ACCOUNT_ID);
        request.setAmount(BigDecimal.TEN.negate());
        request.setOperationId("1");
        return request;
    }

    public static TransactionRequest getTransactionRequestWithoutAmount() {
        TransactionRequest request = new TransactionRequest();
        request.setAccountId(ACCOUNT_ID);
        request.setOperationId("1");
        return request;
    }
}
