package org.example.view;

import org.example.db.entity.Client;

public class ClientInfoView {
    public static void printClientInfo(Client client){
        System.out.println("---------------------- Client info start ------------------");
        System.out.println(String.format("Name: %s %s", client.getFirstName(), client.getSecondName()));
        System.out.println(String.format("Date of birth: %s", client.getDateOfBirth()));
        System.out.println(String.format("INN: %s", client.getInn()));
        System.out.println(String.format("Phone number: %s", client.getPhoneNumber()));
        System.out.println(String.format("Account number: %s", client.getBankAccount()));
        System.out.println(String.format("Balance: %s", client.getBalance()));
        System.out.println("---------------------- Client info end ------------------");
    }
}
