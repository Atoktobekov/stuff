package org.example.service;
import org.example.db.entity.Client;
import java.util.List;

public class ClientService {
    private final List<Client> clients;
    public ClientService(List<Client> clients) {
        this.clients = clients;
    }

    public Client findByFullName(String fullName) {
        return clients.stream()
                .filter(c -> (c.getFirstName() + " " + c.getSecondName()).equals(fullName))
                .findFirst()
                .orElse(null);
    }

}
