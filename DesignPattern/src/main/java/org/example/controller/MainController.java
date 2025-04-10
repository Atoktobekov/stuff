package org.example.controller;

import org.example.db.entity.Client;
import org.example.db.repo.ClientRepository;
import org.example.view.ViewInterface;
import org.example.service.ClientService;
import org.example.service.InfoService;

import java.math.BigDecimal;
import java.util.List;

public class MainController {

    private final List<Client> clients;
    private final ClientRepository clientRepository;
    private final ViewInterface view;
    private final ClientService clientService;
    private final InfoService infoService;

    public MainController(ViewInterface view) {
        this.view = view;
        this.clientRepository = new ClientRepository(); // если он нестатичный
        this.clients = clientRepository.readClientsList();
        this.clientService = new ClientService(clients);
        this.infoService = new InfoService(clients);
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

            Client sender = clientService.findByFullName(senderName);
            Client recipient = clientService.findByFullName(recipientName);
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


    public void handleShowBankInfo() {
        infoService.showBanksInfo(view);
    }

    public void handleShowClientsInfo() {
        infoService.showClientsInfo(view);
    }

}
