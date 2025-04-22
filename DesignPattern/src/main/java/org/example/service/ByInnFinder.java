package org.example.service;
import org.example.db.entity.Client;
import java.util.List;

public class ByInnFinder implements ClientFinderStrategy {
    @Override
    public Client findClient(List<Client> clients, String identifier) {
        return clients.stream()
                .filter(c -> c.getInn().equals(identifier))
                .findFirst()
                .orElse(null);
    }
}
