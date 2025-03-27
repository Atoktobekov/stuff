import java.util.List;

public class StateTaxService {
    private final CurrencyRateRepository currencyRateRepository;

    public StateTaxService() {
        this.currencyRateRepository = new CurrencyRateRepository();
    }

    public void taxByProductCalculations(TaxCalculator calculator, Product product) {
        // Reading currency rates
        List<CurrencyRate> currencyRates = currencyRateRepository.readCurrencyRates();

        // Calculate tax
        double taxAmount = calculator.calculateTax(currencyRates, product);

        // Saving result
        currencyRateRepository.saveTaxInfo(product.getName(), taxAmount);
    }
}

/*
Переопределение и перегрузка методов:
1. Переопределение (Overriding) используется в классах CalculateTaxByDate и CalculateTaxByRate, где переопределяется метод calculateTax из интерфейса TaxCalculator.
2. Перегрузка (Overloading) применяется в классе CurrencyRateRepository, где есть две версии метода readCurrencyRates.

Полиморфизм:
1. Метод taxByProductCalculations в классе StateTaxService использует полиморфизм, принимая любой объект, реализующий TaxCalculator (например, CalculateTaxByDate или CalculateTaxByRate).
*/
