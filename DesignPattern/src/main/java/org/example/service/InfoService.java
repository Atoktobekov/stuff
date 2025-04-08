package org.example.service;

import org.example.db.entity.Bank;
import org.example.db.entity.Client;
import org.example.db.repo.BankRepository;
import org.example.view.ViewInterface;

import java.util.List;

public class InfoService {

    private final List<Client> clients;

    public InfoService(List<Client> clients) {
        this.clients = clients;
    }

    public void showBanksInfo(ViewInterface view) {
        List<Bank> banks = BankRepository.readBanksList();
        StringBuilder info = new StringBuilder();
        for (Bank bank : banks) {
            info.append("Bank Name: ").append(bank.getName()).append("\n")
                    .append("BIC: ").append(bank.getBic()).append("\n")
                    .append("Address: ").append(bank.getAddress()).append("\n")
                    .append("Capital: ").append(bank.getCapital()).append("\n\n");
        }
        view.showInfoDialog("Banks Information", info.toString());
    }

    public void showClientsInfo(ViewInterface view) {
        StringBuilder info = new StringBuilder();
        for (Client client : clients) {
            info.append("Client: ").append(client.getFirstName()).append(" ").append(client.getSecondName()).append("\n")
                    .append("Bank: ").append(client.getBankName()).append("\n")
                    .append("Balance: ").append(client.getBalance()).append("\n")
                    .append("Phone Number: ").append(client.getPhoneNumber()).append("\n")
                    .append("INN: ").append(client.getInn()).append("\n")
                    .append("Date of birth: ").append(client.getDateOfBirth()).append("\n")
                    .append("Bank account: ").append(client.getBankAccount()).append("\n\n");
        }
        view.showInfoDialog("Clients Information", info.toString());
    }
}
