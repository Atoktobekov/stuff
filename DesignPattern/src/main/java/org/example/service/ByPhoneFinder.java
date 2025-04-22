package org.example.service;
import org.example.db.entity.Client;
import java.util.List;

public class ByPhoneFinder implements ClientFinderStrategy {
    @Override
    public Client findClient(List<Client> clients, String identifier) {
        return clients.stream()
                .filter(c -> c.getPhoneNumber().equals(identifier))
                .findFirst()
                .orElse(null);
    }
}

