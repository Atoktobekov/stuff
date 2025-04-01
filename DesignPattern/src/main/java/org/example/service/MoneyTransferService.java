package org.example.service;

import org.example.db.entity.Client;

import java.math.BigDecimal;

public class MoneyTransferService {
    public static void sendMoney(BigDecimal amount, Client sender, Client recipient) throws Exception {
        if (sender.getBalance().compareTo(amount) >= 0) {
            sender.setBalance(sender.getBalance().subtract(amount));
            recipient.setBalance(recipient.getBalance().add(amount));
        } else {
            throw new Exception("You don't have enough funds in your account");
        }
    }
}
