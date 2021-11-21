package com.example.demo.configurations;

import com.example.demo.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class DatabaseConfiguration {

    private final AccountService accountService;

    @Autowired
    public DatabaseConfiguration(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostConstruct
    public void configureUniqueIndex() {
        accountService.createUniqueIndex();
    }
}
