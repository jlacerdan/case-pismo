package com.example.demo.models;

import com.example.demo.models.enums.OperationTypeEnum;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "operations")
public class Operation {
    @Id
    private String id;
    private String description;
    private OperationTypeEnum type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public OperationTypeEnum getType() {
        return type;
    }

    public void setType(OperationTypeEnum type) {
        this.type = type;
    }
}
