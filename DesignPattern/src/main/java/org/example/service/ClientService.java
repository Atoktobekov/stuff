package org.example.service;

import org.example.db.entity.Client;
import org.example.db.repo.ClientRepository;

import java.util.List;

public class ClientService {
    private List<Client> clients;

    public ClientService(List<Client> clients) {
        this.clients = clients;
    }

    public Client findByFullName(String fullName) {
        return clients.stream()
                .filter(c -> (c.getFirstName() + " " + c.getSecondName()).equals(fullName))
                .findFirst()
                .orElse(null);
    }

    public Client findByPhoneNumber(String phoneNumber) {
        return clients.stream()
                .filter(c -> c.getPhoneNumber().equals(phoneNumber))
                .findFirst()
                .orElse(null);
    }

    public Client findByInn(String inn) {
        return clients.stream()
                .filter(c -> c.getInn().equals(inn))
                .findFirst()
                .orElse(null);
    }
}
