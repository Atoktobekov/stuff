package org.example.service;
import org.example.db.entity.Client;

import java.math.BigDecimal;
import java.util.List;


public class MoneyTransferUseCase {
    private final List<Client> clients;
    private final ClientFinderStrategy finder;

    public MoneyTransferUseCase(List<Client> clients, ClientFinderStrategy finder) {
        this.clients = clients;
        this.finder = finder;
    }

    public void transfer(String senderId, String recipientId, BigDecimal amount) throws Exception {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero.");
        }

        Client sender = finder.findClient(clients, senderId);
        Client recipient = finder.findClient(clients, recipientId);

        if (sender == null || recipient == null) {
            throw new Exception("Sender or recipient not found.");
        }

        if (sender.getBalance().compareTo(amount) < 0) {
            throw new Exception("Insufficient funds.");
        }

        sender.setBalance(sender.getBalance().subtract(amount));
        recipient.setBalance(recipient.getBalance().add(amount));
    }
}
