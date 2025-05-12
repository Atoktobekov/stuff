package org.example.controller;

import java.util.Map;

import lombok.Getter;
import org.example.db.entity.Client;
import org.example.db.repo.ClientRepository;
import org.example.service.*;
import org.example.view.ViewInterface;

import java.math.BigDecimal;
import java.util.List;

public class MainController {

    @Getter
    private final List<Client> clients;
    private final ClientRepository clientRepository;
    private final ViewInterface view;
    private final InfoService infoService;
    private final Map<String, ClientFinderStrategy> strategyMap;

    public MainController(ViewInterface view) {
        this.view = view;
        this.clientRepository = new ClientRepository();
        this.clients = clientRepository.readClientsList();
        this.infoService = new InfoService(clients);

        strategyMap = Map.of(
                "By Name", new ByFullNameFinder(),
                "By Phone", new ByPhoneFinder(),
                "By Inn", new ByInnFinder()
        );

    }

    public void handleTransfer(String method, String senderId, String recipientId, String amountText)
    {
        try {
            BigDecimal amount = new BigDecimal(amountText);
            if (amount.compareTo(BigDecimal.ZERO) <= 0) {
                view.showError("Amount must be greater than zero!");
                return;
            }

            ClientFinderStrategy strategy = strategyMap.get(method);
            if (strategy == null) {
                view.showError("Unknown transfer method: " + method);
                return;
            }

            MoneyTransferUseCase useCase = new MoneyTransferUseCase(clients, strategy);
            useCase.transfer(senderId, recipientId, amount);

            Client sender = strategy.findClient(clients, senderId);
            Client recipient = strategy.findClient(clients, recipientId);
            String SenderName = sender.getFirstName() + " " +  sender.getSecondName();
            String RecipientName = recipient.getFirstName() + " " + recipient.getSecondName();

            view.showTransferInfo(SenderName, RecipientName, amount, sender.getBankName(), recipient.getBankName());
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
