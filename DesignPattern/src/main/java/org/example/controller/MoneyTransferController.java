package org.example.controller;

import org.example.db.entity.Client;
import org.example.service.MoneyTransferService;

import java.math.BigDecimal;

public class MoneyTransferController {

    public static void moneyTransfer(BigDecimal amount, Client sender, Client recipient) throws Exception {
        MoneyTransferService.sendMoney(amount, sender, recipient);
    }
}
