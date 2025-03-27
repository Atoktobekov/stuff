import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CurrencyRateRepository {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public List<CurrencyRate> readCurrencyRates() {
        return readCurrencyRates("input.csv");
    }

    public List<CurrencyRate> readCurrencyRates(String fileName) {
        List<CurrencyRate> currencyRates = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(Paths.get(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    LocalDate date = LocalDate.parse(parts[0].replace("\"", ""), DATE_FORMATTER);
                    String currencyCode = parts[1].replace("\"", "").trim();
                    double rate = Double.parseDouble(parts[2].trim());
                    currencyRates.add(new CurrencyRate(date, currencyCode, rate));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return currencyRates;
    }

    public void saveTaxInfo(String productName, double taxAmount) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"))) {
            writer.write(String.format("\"%s\": %.2f сом%n", productName, taxAmount));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
