package org.example.controller;

import org.example.db.entity.Bank;
import org.example.db.entity.Client;
import org.example.db.repo.BankRepository;
import org.example.db.repo.ClientRepository;
import org.example.view.ViewInterface;

import java.math.BigDecimal;
import java.util.List;

public class MainController {

    private final List<Client> clients;
    private final ClientRepository clientRepository;
    private final ViewInterface view;

    public MainController(ViewInterface view) {
        this.view = view;
        this.clientRepository = new ClientRepository(); // если он нестатичный
        this.clients = clientRepository.readClientsList();
    }

    public List<Client> getClients() {
        return clients;
    }

    public void handleTransfer(String senderName, String recipientName, String amountText) {
        try {
            BigDecimal amount = new BigDecimal(amountText);
            if (amount.compareTo(BigDecimal.ZERO) <= 0) {
                view.showError("Amount must be greater than zero!");
                return;
            }

            Client sender = findClientByName(senderName);
            Client recipient = findClientByName(recipientName);

            if (sender == null || recipient == null) {
                view.showError("Sender or recipient not found!");
                return;
            }

            String senderBank = sender.getBankName();
            String recipientBank = recipient.getBankName();

            MoneyTransferController.moneyTransfer(amount, sender, recipient);

            view.showTransferInfo(senderName, recipientName, amount, senderBank, recipientBank);
            view.refreshClientList(clients);

        } catch (NumberFormatException e) {
            view.showError("Invalid amount!");
        } catch (Exception e) {
            view.showError(e.getMessage());
        }
    }

    private Client findClientByName(String fullName) {
        return clients.stream()
                .filter(c -> (c.getFirstName() + " " + c.getSecondName()).equals(fullName))
                .findFirst()
                .orElse(null);
    }

    public void showBanksInfo() {
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

    public void showClientsInfo() {
        StringBuilder info = new StringBuilder();
        for (Client client : clients) {
            info.append("Client: ").append(client.getFirstName()).append(" ").append(client.getSecondName()).append("\n")
                    .append("Bank: ").append(client.getBankName()).append("\n")
                    .append("Balance: ").append(client.getBalance()).append("\n\n");
        }
        view.showInfoDialog("Clients Information", info.toString());
    }

}
