package org.example.view;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.example.controller.MainController;
import org.example.db.entity.Client;

import java.math.BigDecimal;

public class BankUI extends Application implements ViewInterface {

    private MainController controller;
    private ObservableList<String> clientListItems;
    private ComboBox<String> methodComboBox;
    private ComboBox<String> senderComboBox;
    private ComboBox<String> recipientComboBox;
    private TextField amountField;
    private TextArea infoTextArea;

    @Override
    public void start(Stage primaryStage) {
        controller = new MainController(this);
        TabPane tabPane = new TabPane();

        Tab clientTab = new Tab("Clients and Transfers");
        clientTab.setContent(createClientTab());
        clientTab.setClosable(false);

        Tab infoTab = new Tab("Info Viewer");
        infoTab.setContent(createInfoTab());
        infoTab.setClosable(false);

        tabPane.getTabs().addAll(clientTab, infoTab);

        Scene scene = new Scene(tabPane, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Bank Application");
        primaryStage.show();

        refreshClientList(controller.getClients());
    }

    private VBox createClientTab() {
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));

        // Client list
        clientListItems = FXCollections.observableArrayList();
        ListView<String> clientListView = new ListView<>(clientListItems);

        // Transfer section
        methodComboBox = new ComboBox<>();
        methodComboBox.getItems().addAll("By Name", "By Phone", "By Inn");
        methodComboBox.getSelectionModel().selectFirst();
        methodComboBox.setOnAction(event -> refreshClientList(controller.getClients()));

        senderComboBox = new ComboBox<>();
        recipientComboBox = new ComboBox<>();

        amountField = new TextField();
        amountField.setPromptText("Amount");

        Button transferButton = new Button("Transfer Money");
        transferButton.setOnAction(e -> {
            String sender = senderComboBox.getValue();
            String recipient = recipientComboBox.getValue();
            String amount = amountField.getText();
            String method = methodComboBox.getValue();
            controller.handleTransfer(method, sender, recipient, amount);
        });

        layout.getChildren().addAll(new Label("Clients List:"), clientListView,
                new Label("Transfer Method:"), methodComboBox,
                new Label("Sender:"), senderComboBox,
                new Label("Recipient:"), recipientComboBox,
                new Label("Amount:"), amountField,
                transferButton);

        return layout;
    }

    private VBox createInfoTab() {
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));

        infoTextArea = new TextArea();
        infoTextArea.setEditable(false);
        infoTextArea.setWrapText(true);

        Button showClientsButton = new Button("Show Clients Info");
        showClientsButton.setOnAction(e -> controller.handleShowClientsInfo());

        Button showBanksButton = new Button("Show Banks Info");
        showBanksButton.setOnAction(e -> controller.handleShowBankInfo());

        HBox buttons = new HBox(10, showClientsButton, showBanksButton);

        layout.getChildren().addAll(infoTextArea, buttons);
        VBox.setVgrow(infoTextArea, Priority.ALWAYS);

        return layout;
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
    public void refreshClientList(java.util.List<Client> clients) {
        clientListItems.clear();
        senderComboBox.getItems().clear();
        recipientComboBox.getItems().clear();

        String method = methodComboBox.getValue();

        for (Client client : clients) {
            String fullInfo = client.getFirstName() + " " + client.getSecondName() + ", " + client.getPhoneNumber() + ", " + client.getInn() + " - " + client.getBalance();
            clientListItems.add(fullInfo);

            String display;
            switch (method) {
                case "By Phone":
                    display = client.getPhoneNumber(); break;
                case "By INN":
                    display = client.getInn(); break;
                default:
                    display = client.getFirstName() + " " + client.getSecondName();
            }
            senderComboBox.getItems().add(display);
            recipientComboBox.getItems().add(display);
        }
    }

    @Override
    public void showTransferInfo(String senderName, String recipientName, BigDecimal amount, String senderBank, String recipientBank) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Transfer Info");
        alert.setHeaderText(null);
        alert.setContentText("Transferred " + amount + " from " + senderName + " to " + recipientName +
                "\nSender Bank: " + senderBank + "\nRecipient Bank: " + recipientBank);
        alert.showAndWait();
    }

    @Override
    public void showInfoDialog(String title, String content) {
        infoTextArea.setText(content);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
