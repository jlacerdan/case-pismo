package com.example.demo.controllers;

import com.example.demo.exceptions.RequestNotValidException;
import com.example.demo.models.requests.AccountRequest;
import com.example.demo.services.AccountService;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static com.example.demo.mass.DataMass.getAccount;
import static com.example.demo.mass.DataMass.getAccountRequest;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AccountsControllerTest {

    @InjectMocks
    private AccountsController accountsController;

    @Mock
    private AccountService accountService;

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void createAccountWithValidRequestTest() {
        when(accountService.createAccount(any())).thenReturn(getAccount().get());

        accountsController.createAccount(getAccountRequest());

        verify(accountService, times(1)).createAccount(any());
    }

    @Test
    public void createAccountWithInvalidRequestTest() {
        exception.expect(RequestNotValidException.class);
        exception.expectMessage("Account creation request is invalid");
        accountsController.createAccount(new AccountRequest());

        verify(accountService.createAccount(any(AccountRequest.class)), times(0));
    }
}
