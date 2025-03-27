import java.util.List;

public interface TaxCalculator {
    double calculateTax(List<CurrencyRate> currencyRatesList, Product product);
}
