import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Создаём экземпляры классов
        CurrencyRateRepository currencyRateRepository = new CurrencyRateRepository();
        Product product = new Product("Лампа", "EUR", 50); // Пример с продуктом "Лампа"

        System.out.println();

        System.out.println("Параметры продукта: " + product.toString());

        System.out.println();

        // Чтение курсов валют из файла input.csv
        List<CurrencyRate> currencyRates = currencyRateRepository.readCurrencyRates("input.csv");

        // Выводим курсы валют
        System.out.println("Курсы валют из файла input.csv:");
        currencyRates.forEach(System.out::println);

        // Создаём объект для расчёта налога по дате
        TaxCalculator taxCalculator = new CalculateTaxByDate();

        // Расчёт налога на товар по самому свежему курсу
        double taxAmount = taxCalculator.calculateTax(currencyRates, product);


        System.out.println();
        // Выводим результат с двумя знаками после запятой
        System.out.println(String.format("Налог на товар (с расчётом по самому свежему курсу): %.2f", taxAmount));

        // Сохраняем информацию о налоге в файл output.txt
        currencyRateRepository.saveTaxInfo(product.getName(), taxAmount);
    }
}
