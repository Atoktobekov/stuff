package org.example.view;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.controller.MainController;
import org.example.db.entity.Client;
import org.example.db.entity.Bank;

import java.math.BigDecimal;
import java.util.List;

public class BankUI extends Application implements ViewInterface {

    private ObservableList<String> clientListItems;
    private ListView<String> clientListView;
    private ComboBox<String> senderComboBox;
    private ComboBox<String> recipientComboBox;
    private TextField amountField;
    private MainController controller;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Bank System");

        controller = new MainController(this);

        VBox clientListPanel = new VBox(10);
        clientListPanel.setStyle("-fx-padding: 10;");
        clientListPanel.getChildren().add(new Label("Client List"));

        clientListItems = FXCollections.observableArrayList();
        clientListView = new ListView<>(clientListItems);
        clientListPanel.getChildren().add(clientListView);

        VBox transferPanel = new VBox(10);
        transferPanel.setStyle("-fx-padding: 10;");
        transferPanel.getChildren().add(new Label("Money Transfer"));

        amountField = new TextField();
        amountField.setPromptText("Amount to transfer");

        senderComboBox = new ComboBox<>();
        recipientComboBox = new ComboBox<>();


        for (Client client : controller.getClients()) {
            String name = client.getFirstName() + " " + client.getSecondName() + " " + client.getBankName();
            senderComboBox.getItems().add(name);
            recipientComboBox.getItems().add(name);
        }

        Button transferButton = new Button("Transfer Money");
        transferButton.setOnAction(event -> {
            String senderName = senderComboBox.getValue();
            String recipientName = recipientComboBox.getValue();
            String amountText = amountField.getText();
            controller.handleTransfer(senderName, recipientName, amountText);
        });

        transferPanel.getChildren().addAll(amountField, senderComboBox, recipientComboBox, transferButton);

        Button showBanksInfoButton = new Button("Show Banks Info");
        showBanksInfoButton.setOnAction(event -> controller.showBanksInfo());

        Button showClientsInfoButton = new Button("Show Clients Info");
        showClientsInfoButton.setOnAction(event -> controller.showClientsInfo());

        VBox infoButtonsPanel = new VBox(10);
        infoButtonsPanel.setStyle("-fx-padding: 10;");
        infoButtonsPanel.getChildren().addAll(showBanksInfoButton, showClientsInfoButton);

        VBox root = new VBox(20);
        root.getChildren().addAll( clientListPanel, transferPanel, infoButtonsPanel);

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();

        refreshClientList(controller.getClients());
    }

    @Override
    public void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @Override
    public void refreshClientList(List<Client> clients) {
        clientListItems.clear();
        senderComboBox.getItems().clear();
        recipientComboBox.getItems().clear();

        for (Client client : clients) {
            String display = client.getFirstName() + " " + client.getSecondName() + ", "+ client.getBankName() +  " - " + client.getBalance();
            clientListItems.add(display);

            String name = client.getFirstName() + " " + client.getSecondName();
            senderComboBox.getItems().add(name);
            recipientComboBox.getItems().add(name);
        }
    }

    @Override
    public void showTransferInfo(String senderName, String recipientName, BigDecimal amount, String senderBank, String recipientBank) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Transfer Information");
        alert.setHeaderText(null);
        alert.setContentText("Transfer Details:\n" +
                "Sender: " + senderName + "\n" +
                "Sender's Bank: " + senderBank + "\n" +
                "Recipient: " + recipientName + "\n" +
                "Recipient's Bank: " + recipientBank + "\n" +
                "Amount: " + amount);
        alert.showAndWait();
    }

    @Override
    public void showInfoDialog(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
