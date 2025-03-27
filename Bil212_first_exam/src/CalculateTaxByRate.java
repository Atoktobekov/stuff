import java.util.List;
import java.util.Comparator;

public class CalculateTaxByRate implements TaxCalculator {
    @Override
    public double calculateTax(List<CurrencyRate> currencyRatesList, Product product) {
        // find the highest Currency rate
        return currencyRatesList.stream()
                .filter(rate -> rate.getCurrencyCode().equals(product.getCurrencyCode()))
                .max(Comparator.comparing(CurrencyRate::getRate))
                .map(rate -> (product.getPrice() * rate.getRate()) * 0.1) // Convert and get 10% tax
                .orElse(0.0); // If not found, tax is Zero
    }
}
