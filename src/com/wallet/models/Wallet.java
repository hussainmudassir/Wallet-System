package com.wallet.models;

import java.util.*;

public class Wallet {
    private Double balance;
    private String walletId;
    List<Transaction> statement;
    private User user;

    public Wallet(Double amount, String name) {
        this.balance = amount;
        this.walletId = UUID.randomUUID().toString();
        this.statement = new LinkedList<>();
        this.user = new User(name, this.walletId);
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public List<Transaction> getStatement() {
        return statement;
    }
    public void setStatement(List<Transaction> statement) {
        this.statement = statement;
    }

}
