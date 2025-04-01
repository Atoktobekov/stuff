package org.example.controller;

import org.example.db.repo.ClientRepository;
import org.example.view.ErrorView;
import org.example.db.entity.Client;

import java.util.ArrayList;
import java.util.List;


public class ReadClientsListController {
    public static List<Client> readClientsList(){
        List<Client> clientsList = new ArrayList<>();
        try{
           clientsList = ClientRepository.readClientsList();
        }
        catch (Exception e) {
            ErrorView.printErrorMessage(e.getMessage());
        }
        return clientsList;
    }
}
