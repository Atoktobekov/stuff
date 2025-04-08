package org.example.db.repo;

import org.example.db.entity.Client;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ClientRepository {

    public static List<Client> readClientsList() {
        List<Client> clientsList = new ArrayList<>();
        DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try (BufferedReader br = new BufferedReader(new FileReader("input.csv"))) {
            String line;
            br.readLine(); // Skip header line
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length == 8) { // добавлено одно поле
                    Client client = new Client(
                            values[0],
                            values[1],
                            LocalDate.parse(values[2], DATE_FORMATTER),
                            values[3],
                            values[4],
                            values[5],
                            new BigDecimal(values[6]),
                            values[7]  // поле банка
                    );
                    clientsList.add(client);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return clientsList;
    }
}
