package com.example.demo.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import static com.example.demo.constants.Constants.ACCOUNT_COLLECTION;

@Document(collection = ACCOUNT_COLLECTION)
public class Account {

    @Id
    private String accountId;
    private String documentNumber;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }
}
