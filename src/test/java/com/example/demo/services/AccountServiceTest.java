package com.example.demo.services;

import com.example.demo.exceptions.AccountAlreadyExistsForDocumentException;
import com.example.demo.exceptions.AccountNotFoundException;
import com.example.demo.models.Account;
import com.example.demo.repositories.AccountRepository;
import com.mongodb.DuplicateKeyException;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static com.example.demo.mass.DataMass.ACCOUNT_ID;
import static com.example.demo.mass.DataMass.DOCUMENT_NUMBER;
import static com.example.demo.mass.DataMass.getAccount;
import static com.example.demo.mass.DataMass.getAccountRequest;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest {

    @InjectMocks
    private AccountService accountService;

    @Mock
    private AccountRepository accountRepository;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void getAccountByIdSuccessfullyTest() {
        when(accountRepository.findById(anyString())).thenReturn(getAccount());

        Account account = accountService.getAccountById("1");

        Assert.assertNotNull(account);
        Assert.assertEquals(ACCOUNT_ID, account.getAccountId());
        Assert.assertEquals(DOCUMENT_NUMBER, account.getDocumentNumber());
    }

    @Test
    public void getAccountByIdNotFoundTest() {
        when(accountRepository.findById(anyString())).thenReturn(Optional.empty());

        exception.expect(AccountNotFoundException.class);
        exception.expectMessage("Account with id 1 does not exist.");

        accountService.getAccountById("1");
    }

    @Test
    public void createAccountSuccessfullyTest() {
        when(accountRepository.save(any(Account.class))).thenReturn(getAccount().get());

        Account account = accountService.createAccount(getAccountRequest());

        Assert.assertNotNull(account);
        Assert.assertEquals(DOCUMENT_NUMBER, account.getDocumentNumber());
    }

    @Test
    public void createAccountWhenDocumentNumberAlreadyExistsTest() {
        when(accountRepository.save(any(Account.class))).thenThrow(DuplicateKeyException.class);

        exception.expect(AccountAlreadyExistsForDocumentException.class);
        exception.expectMessage("Account already exists for document 1234567890");

        accountService.createAccount(getAccountRequest());
    }
}
