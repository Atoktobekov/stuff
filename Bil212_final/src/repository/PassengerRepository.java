package repository;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class PassengerRepository {
    public void saveReport(String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"))) {
            writer.write(content);
            System.out.println("Report saved to output.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
