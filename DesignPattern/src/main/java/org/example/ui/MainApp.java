package org.example.ui;

import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.example.db.entity.Client;
import org.example.controller.MoneyTransferController;
import org.example.db.repo.ClientRepository;

import java.math.BigDecimal;
import java.util.List;

public class MainApp extends Application {

    private List<Client> clients;
    private ObservableList<String> clientListItems;

    @Override
    public void start(Stage primaryStage) {
        // Создаем основные компоненты UI
        primaryStage.setTitle("Bank System");

        // Панель для отображения информации о банке
        VBox bankInfoPanel = new VBox();
        bankInfoPanel.setSpacing(10);
        bankInfoPanel.setStyle("-fx-padding: 10;");

        // Информация о банке
        Label bankInfoLabel = new Label("Bank Info");
        Label bankName = new Label("Bank Name: O! Bank");
        Label bankBIC = new Label("BIC: 912456");
        Label bankAddress = new Label("Address: Bishkek, Frunze 451");
        Label bankCapital = new Label("Capital: 123456789.05");

        bankInfoPanel.getChildren().addAll(bankInfoLabel, bankName, bankBIC, bankAddress, bankCapital);

        // Панель для отображения списка клиентов
        VBox clientListPanel = new VBox();
        clientListPanel.setSpacing(10);
        clientListPanel.setStyle("-fx-padding: 10;");
        Label clientListLabel = new Label("Client List");

        // Список клиентов (будем использовать ObservableList)
        ListView<String> clientListView = new ListView<>();
        clients = ClientRepository.readClientsList(); // Получить список клиентов из репозитория или создать вручную
        clientListItems = FXCollections.observableArrayList();

        // Добавляем клиентов в ObservableList
        updateClientListItems();

        clientListView.setItems(clientListItems);

        clientListPanel.getChildren().addAll(clientListLabel, clientListView);

        // Панель для перевода средств
        VBox transferPanel = new VBox();
        transferPanel.setSpacing(10);
        transferPanel.setStyle("-fx-padding: 10;");
        Label transferLabel = new Label("Money Transfer");

        // Поля для ввода данных перевода
        TextField amountField = new TextField();
        amountField.setPromptText("Amount to transfer");
        ComboBox<String> senderComboBox = new ComboBox<>();
        ComboBox<String> recipientComboBox = new ComboBox<>();
        for (Client client : clients) {
            senderComboBox.getItems().add(client.getFirstName() + " " + client.getSecondName());
            recipientComboBox.getItems().add(client.getFirstName() + " " + client.getSecondName());
        }

        // Кнопка для выполнения перевода
        Button transferButton = new Button("Transfer Money");
        transferButton.setOnAction(event -> {
            String senderName = senderComboBox.getValue();
            String recipientName = recipientComboBox.getValue();
            BigDecimal amount = new BigDecimal(amountField.getText());

            // Получаем клиентов по имени
            Client sender = clients.stream().filter(c -> (c.getFirstName() + " " + c.getSecondName()).equals(senderName)).findFirst().orElse(null);
            Client recipient = clients.stream().filter(c -> (c.getFirstName() + " " + c.getSecondName()).equals(recipientName)).findFirst().orElse(null);

            if (sender != null && recipient != null) {
                // Вызов контроллера для перевода средств
                MoneyTransferController.moneyTransfer(amount, sender, recipient);

                // Обновляем список клиентов после перевода
                updateClientListItems();
            } else {
                // Ошибка: клиенты не найдены
                showErrorDialog("Error", "Sender or recipient not found!");
            }
        });

        transferPanel.getChildren().addAll(transferLabel, amountField, senderComboBox, recipientComboBox, transferButton);

        // Главная панель
        VBox root = new VBox();
        root.setSpacing(20);
        root.getChildren().addAll(bankInfoPanel, clientListPanel, transferPanel);

        // Настройка сцены
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Метод для обновления списка клиентов в ListView
    private void updateClientListItems() {
        clientListItems.clear();
        for (Client client : clients) {
            clientListItems.add(client.getFirstName() + " " + client.getSecondName() + " - " + client.getBalance());
        }
    }

    // Метод для отображения ошибок в диалоговом окне
    private void showErrorDialog(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        // Запускаем приложение через JavaFX
        launch(args); // Этот метод вызовет start(Stage primaryStage)
    }
}
