package org.example.view;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.example.db.entity.Client;
import org.example.db.entity.Bank;
import org.example.controller.MoneyTransferController;
import org.example.controller.ReadClientsListController;

import java.math.BigDecimal;
import java.util.List;

public class BankUI extends Application {
    public static Bank bank;
    private List<Client> clients;
    private ObservableList<String> clientListItems;

    @Override
    public void start(Stage primaryStage) {
        // Создаем основные компоненты UI
        primaryStage.setTitle("Bank System");

        // bank info panel
        VBox bankInfoPanel = new VBox();
        bankInfoPanel.setSpacing(10);
        bankInfoPanel.setStyle("-fx-padding: 10;");

        // bank info
        Label bankInfoLabel = new Label("Bank Info");
        Label bankName = new Label("Bank Name: " + bank.getName());
        Label bankBIC = new Label("BIC: " + bank.getBic());
        Label bankAddress = new Label("Address: " + bank.getAddress());
        Label bankCapital = new Label("Capital: " + bank.getCapital());

        bankInfoPanel.getChildren().addAll(bankInfoLabel, bankName, bankBIC, bankAddress, bankCapital);

        // clients list panel
        VBox clientListPanel = new VBox();
        clientListPanel.setSpacing(10);
        clientListPanel.setStyle("-fx-padding: 10;");
        Label clientListLabel = new Label("Client List");

        // clients list(using observable list)
        ListView<String> clientListView = new ListView<>();
        clients = ReadClientsListController.readClientsList();
        clientListItems = FXCollections.observableArrayList();

        // add clients to ObservableList
        updateClientListItems();

        clientListView.setItems(clientListItems);

        clientListPanel.getChildren().addAll(clientListLabel, clientListView);

        // money transfer panel
        VBox transferPanel = new VBox();
        transferPanel.setSpacing(10);
        transferPanel.setStyle("-fx-padding: 10;");
        Label transferLabel = new Label("Money Transfer");

        // field for transfer data
        TextField amountField = new TextField();
        amountField.setPromptText("Amount to transfer");
        ComboBox<String> senderComboBox = new ComboBox<>();
        ComboBox<String> recipientComboBox = new ComboBox<>();
        for (Client client : clients) {
            senderComboBox.getItems().add(client.getFirstName() + " " + client.getSecondName());
            recipientComboBox.getItems().add(client.getFirstName() + " " + client.getSecondName());
        }

        // transfer button
        Button transferButton = new Button("Transfer Money");
        transferButton.setOnAction(event -> {
            String senderName = senderComboBox.getValue();
            String recipientName = recipientComboBox.getValue();
            BigDecimal amount = new BigDecimal(amountField.getText());

            // get clients by name
            Client sender = clients.stream().filter(c -> (c.getFirstName() + " " + c.getSecondName()).equals(senderName)).findFirst().orElse(null);
            Client recipient = clients.stream().filter(c -> (c.getFirstName() + " " + c.getSecondName()).equals(recipientName)).findFirst().orElse(null);

            if (sender != null && recipient != null) {
                MoneyTransferController.moneyTransfer(amount, sender, recipient);

                updateClientListItems();
            } else {
                showErrorDialog("Error", "Sender or recipient not found!");
            }
        });

        transferPanel.getChildren().addAll(transferLabel, amountField, senderComboBox, recipientComboBox, transferButton);

        // Main panel
        VBox root = new VBox();
        root.setSpacing(20);
        root.getChildren().addAll(bankInfoPanel, clientListPanel, transferPanel);

        //setting scene
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // client list update in ListView
    private void updateClientListItems() {
        clientListItems.clear();
        for (Client client : clients) {
            clientListItems.add(client.getFirstName() + " " + client.getSecondName() + " - " + client.getBalance());
        }
    }

    // error show method
    private void showErrorDialog(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args, Bank oBank) {
        bank = oBank; // Передача банка в статическое поле
        launch(args);
    }
}
