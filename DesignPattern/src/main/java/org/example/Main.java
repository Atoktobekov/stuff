package org.example;

import org.example.db.entity.Bank;
import org.example.view.BankUI;

import java.math.BigDecimal;

public class Main { // Design pattern
    public static void main(String[] args) {
        Bank oBank = new Bank("Jew Jitsu Bank", "9772477", "Israel, Yaffo 1488", new BigDecimal("123456789.05"));
        BankUI.main(args);
    }
}