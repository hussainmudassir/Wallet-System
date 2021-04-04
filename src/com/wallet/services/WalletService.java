package com.wallet.services;

import com.wallet.WalletException;
import com.wallet.models.Transaction;
import com.wallet.models.TransactionType;
import com.wallet.models.Wallet;

import java.util.List;
import java.util.Map;

public class WalletService {

    private static final double minimumAmount = 0.0001;

    public void createWallet(Double amount, String name, Map<String, Wallet> usersWalletMap, Map<String, Double> overview) throws WalletException {
        if(usersWalletMap.containsKey(name)) throw new WalletException();
        Wallet wallet = new Wallet(amount, name);
        usersWalletMap.put(name, wallet);
        overview.put(name, amount);
    }

    public void transfer(String sender, String receiver, Double amount, Map<String, Wallet> usersWalletMap, Map<String, Double> overview) throws WalletException {
        if(amount < minimumAmount) {
            throw new WalletException();
        }
        Wallet senderWallet = usersWalletMap.get(sender);
        Wallet receiverWallet = usersWalletMap.get(receiver);
        if(senderWallet == null || receiverWallet == null) {
            throw new WalletException();
        }
        Double currentSenderBal = senderWallet.getBalance();
        Double currentReceiverBal = receiverWallet.getBalance();

        if(currentSenderBal - amount < 0) {
            throw new WalletException();
        }

        Double updatedSenderBal = currentSenderBal - amount;
        Double updatedReceiverBal = currentReceiverBal + amount;
        List<Transaction> senderStat = senderWallet.getStatement();
        List<Transaction> receiverStat = receiverWallet.getStatement();
        senderStat.add(new Transaction(receiver, amount, TransactionType.DEBIT));
        receiverStat.add(new Transaction(sender, amount, TransactionType.CREDIT));

        if(currentReceiverBal + amount == currentSenderBal - amount) {
            updatedReceiverBal += 10;
            updatedSenderBal += 10;
            senderStat.add(new Transaction("offer1",10.0, TransactionType.CREDIT ));
            receiverStat.add(new Transaction("offer1",10.0, TransactionType.CREDIT ));
        }

        senderWallet.setBalance(updatedSenderBal);
        receiverWallet.setBalance(updatedReceiverBal);
        overview.put(sender, updatedSenderBal);
        overview.put(receiver, updatedReceiverBal);


        senderWallet.setStatement(senderStat);
        receiverWallet.setStatement(receiverStat);

        usersWalletMap.put(sender, senderWallet);
        usersWalletMap.put(receiver, receiverWallet);
    }

    public List<Transaction> getStatement(String name, Map<String, Wallet> usersWallet) throws WalletException{
        if(!usersWallet.containsKey(name)) {
            throw new WalletException();
        }
        return usersWallet.get(name).getStatement();
    }
}
