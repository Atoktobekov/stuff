package org.example.view;

import org.example.db.entity.Client;

import java.math.BigDecimal;
import java.util.List;

public interface ViewInterface {

    void showError(String message);

    void refreshClientList(List<Client> clients);

    void showTransferInfo(String senderName, String recipientName, BigDecimal amount, String senderBank, String recipientBank);

    void showInfoDialog(String title, String content);
}
