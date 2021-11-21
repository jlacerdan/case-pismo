package com.example.demo.controllers;

import com.example.demo.exceptions.RequestNotValidException;
import com.example.demo.models.Account;
import com.example.demo.models.requests.AccountRequest;
import com.example.demo.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounts/")
public class AccountsController {

    private final AccountService accountService;

    @Autowired
    public AccountsController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody AccountRequest accountRequest) {
        if (!isAccountRequestValid(accountRequest)) {
            throw new RequestNotValidException("Account creation request is invalid");
        }
        return new ResponseEntity<>(accountService.createAccount(accountRequest), HttpStatus.CREATED);
    }

    private boolean isAccountRequestValid(AccountRequest accountRequest) {
        return StringUtils.hasText(accountRequest.getDocumentNumber());
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<Account> getAccountById(@PathVariable String accountId) {
        return new ResponseEntity<>(accountService.getAccountById(accountId), HttpStatus.OK);
    }
}
