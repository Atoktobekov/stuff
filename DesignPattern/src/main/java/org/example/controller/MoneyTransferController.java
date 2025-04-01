package org.example.controller;

import org.example.db.entity.Client;
import org.example.service.MoneyTransferService;
import org.example.view.ClientInfoView;
import org.example.view.ErrorView;

import java.math.BigDecimal;

public class MoneyTransferController {
    public static void moneyTransfer(BigDecimal amount, Client sender, Client recipient){
        try{
            MoneyTransferService.sendMoney(amount, sender, recipient);
        } catch (Exception e) {
            ErrorView.printErrorMessage(e.getMessage());
        }
        ClientInfoView.printClientInfo(sender);
        ClientInfoView.printClientInfo(recipient);
    }
}
