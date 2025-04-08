package org.example.db.repo;

import org.example.db.entity.Bank;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class BankRepository {

    public static List<Bank> readBanksList() {
        List<Bank> banksList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("BanksInput.csv"))) {
            String line;
            br.readLine(); // Skip header line
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length == 4) {
                    Bank bank = new Bank(
                            values[0], // Name
                            values[1], // BIC
                            values[2], // Address
                            new BigDecimal(values[3]) // Capital
                    );
                    banksList.add(bank);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return banksList;
    }
}
