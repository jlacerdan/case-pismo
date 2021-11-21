package com.example.demo.services;

import com.example.demo.exceptions.AccountAlreadyExistsForDocumentException;
import com.example.demo.exceptions.AccountNotFoundException;
import com.example.demo.models.Account;
import com.example.demo.models.requests.AccountRequest;
import com.example.demo.repositories.AccountRepository;
import com.mongodb.DuplicateKeyException;
import com.mongodb.client.model.IndexOptions;
import com.mongodb.client.model.Indexes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;

import static com.example.demo.constants.Constants.ACCOUNT_COLLECTION;
import static com.example.demo.constants.Constants.DOCUMENT_NUMBER_FIELD;

@Service
public class AccountService {

    private AccountRepository accountRepository;
    private MongoOperations mongoOperations;

    @Autowired
    public AccountService(AccountRepository accountRepository, MongoOperations mongoOperations) {
        this.accountRepository = accountRepository;
        this.mongoOperations = mongoOperations;
    }

    public Account getAccountById(String accountId) {
        return accountRepository.findById(accountId).orElseThrow(() -> new AccountNotFoundException(accountId));
    }

    public Account createAccount(AccountRequest accountRequest) {
        Account account = new Account();
        account.setDocumentNumber(accountRequest.getDocumentNumber());
        try {
            return accountRepository.save(account);
        } catch (DuplicateKeyException ex) {
            throw new AccountAlreadyExistsForDocumentException(account.getDocumentNumber());
        }
    }

    public void createUniqueIndex() {
        IndexOptions indexOptions = new IndexOptions();
        indexOptions.name("idx_documentNumber");
        indexOptions.unique(true);
        indexOptions.background(true);

        mongoOperations.getCollection(ACCOUNT_COLLECTION).createIndex(Indexes.ascending(DOCUMENT_NUMBER_FIELD), indexOptions);
    }
}
