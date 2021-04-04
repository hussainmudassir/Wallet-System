package com.wallet.models;

public class User {

    private String name;

    private String walletId;

    public User(String name, String walletId) {
        this.name = name;
        this.walletId = walletId;
    }

    public String getName() {
        return name;
    }

    public String getWalletId() {
        return walletId;
    }
}
