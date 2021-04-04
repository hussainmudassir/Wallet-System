package com.wallet.models;

public class Transaction {
    private String username;
    private Double amount;
    private TransactionType transactionType;

    public Transaction(String name, Double amount, TransactionType transactionType) {
        this.transactionType = transactionType;
        this.amount = amount;
        this.username = name;
    }

    public String getUsername() {
        return username;
    }

    public Double getAmount() {
        return amount;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }
}
