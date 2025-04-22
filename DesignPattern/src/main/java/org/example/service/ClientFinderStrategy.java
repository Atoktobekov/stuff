package org.example.service;

import org.example.db.entity.Client;
import java.util.List;
public interface ClientFinderStrategy {
    Client findClient(List<Client> clients, String identifier);
}
