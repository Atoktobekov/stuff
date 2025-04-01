package org.example.view;

import org.example.db.entity.Bank;

public class BankInfoView {
    public static void printBankInfo(Bank bank){
        System.out.println("---------------------- Bank info start ------------------");
        System.out.println("Bank name: " + bank.getName());
        System.out.println("Bank bic: " + bank.getBic());
        System.out.println("Bank address: " + bank.getAddress());
        System.out.println("Bank capital: " + bank.getCapital());
        System.out.println("---------------------- Bank info end ------------------");
    }
}
