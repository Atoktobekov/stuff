package org.example;

import org.example.controller.MoneyTransferController;
import org.example.db.entity.Bank;
import org.example.db.entity.Client;
import org.example.db.repo.ClientRepository;
import org.example.service.MoneyTransferService;
import org.example.ui.MainApp;
import org.example.view.BankInfoView;
import org.example.view.ClientInfoView;
import org.example.view.ErrorView;

import java.math.BigDecimal;
import java.util.List;

import static javafx.application.Application.launch;

public class Main { // Design pattern
    public static void main(String[] args) {
        Bank oBank = new Bank("O! Bank", "912456", "Bishkek, Frunze 451", new BigDecimal("123456789.05"));
        BankInfoView.printBankInfo(oBank);
        BigDecimal amount = new BigDecimal("500");

        List<Client> clientList = ClientRepository.readClientsList();
        Client john = clientList.get(0);
        Client jane = clientList.get(1);
        MoneyTransferController.moneyTransfer(amount, john, jane);
        MainApp.main(args);
    }
}