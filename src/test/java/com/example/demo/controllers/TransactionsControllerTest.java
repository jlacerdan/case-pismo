package com.example.demo.controllers;

import com.example.demo.exceptions.RequestNotValidException;
import com.example.demo.services.TransactionService;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static com.example.demo.mass.DataMass.getTransaction;
import static com.example.demo.mass.DataMass.getTransactionRequest;
import static com.example.demo.mass.DataMass.getTransactionRequestWithAmountZero;
import static com.example.demo.mass.DataMass.getTransactionRequestWithNegativeAmount;
import static com.example.demo.mass.DataMass.getTransactionRequestWithoutAccountId;
import static com.example.demo.mass.DataMass.getTransactionRequestWithoutAmount;
import static com.example.demo.mass.DataMass.getTransactionRequestWithoutOperationId;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TransactionsControllerTest {

    @InjectMocks
    private TransactionsController transactionsController;

    @Mock
    private TransactionService transactionService;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void createTransactionSuccessfullyTest() {
        when(transactionService.createTransaction(any())).thenReturn(getTransaction());

        transactionsController.createTransaction(getTransactionRequest());

        verify(transactionService, times(1)).createTransaction(any());
    }

    @Test
    public void createTransactionWhenTransactionRequestHasNegativeAmount() {
        exception.expect(RequestNotValidException.class);

        transactionsController.createTransaction(getTransactionRequestWithNegativeAmount());
    }

    @Test
    public void createTransactionWhenTransactionRequestHasAmountZero() {
        exception.expect(RequestNotValidException.class);

        transactionsController.createTransaction(getTransactionRequestWithAmountZero());
    }

    @Test
    public void createTransactionWhenTransactionRequestHasNoAmount() {
        exception.expect(RequestNotValidException.class);

        transactionsController.createTransaction(getTransactionRequestWithoutAmount());
    }

    @Test
    public void createTransactionWhenTransactionRequestHasNoAccountId() {
        exception.expect(RequestNotValidException.class);

        transactionsController.createTransaction(getTransactionRequestWithoutAccountId());
    }

    @Test
    public void createTransactionWhenTransactionRequestHasNoOperationId() {
        exception.expect(RequestNotValidException.class);

        transactionsController.createTransaction(getTransactionRequestWithoutOperationId());
    }


}
