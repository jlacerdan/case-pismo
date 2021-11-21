package com.example.demo.services;

import com.example.demo.exceptions.AccountNotFoundException;
import com.example.demo.exceptions.InvalidOperationTypeException;
import com.example.demo.exceptions.OperationNotFoundException;
import com.example.demo.models.Transaction;
import com.example.demo.models.enums.OperationTypeEnum;
import com.example.demo.repositories.TransactionRepository;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;

import static com.example.demo.mass.DataMass.getAccount;
import static com.example.demo.mass.DataMass.getOperationWithTypeCredit;
import static com.example.demo.mass.DataMass.getOperationWithTypeDebit;
import static com.example.demo.mass.DataMass.getTransaction;
import static com.example.demo.mass.DataMass.getTransactionRequest;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TransactionServiceTest {

    @InjectMocks
    private TransactionService transactionService;

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private AccountService accountService;

    @Mock
    private OperationService operationService;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void createDebitTransactionSuccessfullyTest() {
        when(accountService.getAccountById(any())).thenReturn(getAccount().get());
        when(operationService.getOperationById(any())).thenReturn(getOperationWithTypeDebit());
        when(transactionRepository.save(any(Transaction.class))).thenReturn(getTransaction());

        Transaction transaction = transactionService.createTransaction(getTransactionRequest());

        Assert.assertNotNull(transaction);
    }

    @Test
    public void createCreditTransactionSuccessfullyTest() {
        when(accountService.getAccountById(any())).thenReturn(getAccount().get());
        when(operationService.getOperationById(any())).thenReturn(getOperationWithTypeCredit());
        when(transactionRepository.save(any(Transaction.class))).thenReturn(getTransaction());

        Transaction transaction = transactionService.createTransaction(getTransactionRequest());

        Assert.assertNotNull(transaction);
    }

    @Test
    public void createTransactionWhenAccountIdIsNotFoundTest() {
        when(accountService.getAccountById(any())).thenThrow(AccountNotFoundException.class);

        exception.expect(AccountNotFoundException.class);

        transactionService.createTransaction(getTransactionRequest());
    }

    @Test
    public void createTransactionWhenOperationIdIsNotFoundTest() {
        when(accountService.getAccountById(any())).thenReturn(getAccount().get());
        when(operationService.getOperationById(any())).thenThrow(OperationNotFoundException.class);

        exception.expect(OperationNotFoundException.class);

        transactionService.createTransaction(getTransactionRequest());
    }

    @Test
    public void setTransactionAmountWithTypeCreditTest() {
        BigDecimal creditOperationAmount = transactionService.setTransactionAmountByOperationType(BigDecimal.valueOf(10L), OperationTypeEnum.CREDIT);
        Assert.assertEquals(BigDecimal.valueOf(10L), creditOperationAmount);
    }

    @Test
    public void setTransactionAmountWithTypeDebitTest() {
        BigDecimal debitOperationAmount = transactionService.setTransactionAmountByOperationType(BigDecimal.valueOf(10L), OperationTypeEnum.DEBIT);
        Assert.assertEquals(BigDecimal.valueOf(-10L), debitOperationAmount);
    }

    @Test
    public void setTransactionAmountWithoutOperationTypeTest() {
        exception.expect(InvalidOperationTypeException.class);

        BigDecimal debitOperationAmount = transactionService.setTransactionAmountByOperationType(BigDecimal.valueOf(10L), null);
    }

}
