package com.wallet;

import com.wallet.models.Transaction;
import com.wallet.models.Wallet;
import com.wallet.services.WalletService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws WalletException {
        Map<String, Double> overview = new HashMap<>();
        Map<String, Wallet> usersWallet = new HashMap<>();
        WalletService walletService = new WalletService();

        walletService.createWallet(100.0, "Sanyam", usersWallet, overview);
        walletService.createWallet(95.7, "Aatish", usersWallet, overview);
        walletService.createWallet(104.0, "Apurva", usersWallet, overview);
        walletService.createWallet(200.0, "Deep", usersWallet, overview);
        walletService.createWallet(500.0, "Praj", usersWallet, overview);
        System.out.println(overview);

        walletService.transfer("Deep", "Praj", 30.0, usersWallet, overview);
        walletService.transfer("Apurva", "Sanyam", 2.0, usersWallet, overview);
        walletService.transfer("Deep", "Aatish", 5.0, usersWallet, overview);
        System.out.println(overview);
        List<Transaction> statement = walletService.getStatement("Sanyam", usersWallet);
        System.out.println("Statement of Sanyam");
        for(Transaction t : statement) {
            System.out.println(t.getUsername() + " " + t.getTransactionType() + " " + t.getAmount());
        }
        List<Transaction> statement1 = walletService.getStatement("Deep", usersWallet);
        System.out.println("Statement of Deep");
        for(Transaction t : statement1) {
            System.out.println(t.getUsername() + " " + t.getTransactionType() + " " + t.getAmount());
        }
    }
}
