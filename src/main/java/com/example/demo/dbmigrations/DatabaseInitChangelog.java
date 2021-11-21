package com.example.demo.dbmigrations;

import com.example.demo.models.Operation;
import com.example.demo.models.enums.OperationTypeEnum;
import com.example.demo.repositories.OperationRepository;
import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;

@ChangeLog(order = "001")
public class DatabaseInitChangelog {

    @ChangeSet(order = "001", id = "default_operations", author = "joao")
    public void createDefaultOperations(OperationRepository operationRepository) {
        Operation withdrawOperation = new Operation();
        withdrawOperation.setId("1");
        withdrawOperation.setDescription("Withdraw");
        withdrawOperation.setType(OperationTypeEnum.DEBIT);
        Operation depositOperation = new Operation();
        depositOperation.setId("2");
        depositOperation.setDescription("Deposit");
        depositOperation.setType(OperationTypeEnum.CREDIT);

        operationRepository.save(withdrawOperation);
        operationRepository.save(depositOperation);
    }

}
