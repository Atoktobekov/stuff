package org.example;

import org.example.db.entity.Bank;
import org.example.view.BankUI;
import org.example.view.BankInfoView;

import java.math.BigDecimal;

import static javafx.application.Application.launch;

public class Main { // Design pattern
    public static void main(String[] args) {
        Bank oBank = new Bank("Jew Jitsu Bank", "9772477", "Israel, Yaffo 1488", new BigDecimal("123456789.05"));
        BankInfoView.printBankInfo(oBank);
        BankUI.main(args, oBank);
    }
}