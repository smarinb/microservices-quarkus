package org.acme.transaction.model;

import java.time.LocalDate;

import io.quarkus.mongodb.panache.PanacheMongoEntity;

public class Transaction extends PanacheMongoEntity{

    public String account;
    public Double value;
    public LocalDate date;
    public TransactionType type;
    public String description;

    
    public Transaction() {
    }

    public String getAccount() {
        return account;
    }


    public void setAccount(String account) {
        this.account = account;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    

    
}
