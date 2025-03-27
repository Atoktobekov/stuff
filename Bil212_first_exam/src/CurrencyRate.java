import java.time.LocalDate;

public class CurrencyRate {
    private LocalDate date;
    private String currencyCode;
    private double rate;

    public CurrencyRate(LocalDate date, String currencyCode, double rate) {
        this.date = date;
        this.currencyCode = currencyCode;
        this.rate = rate;
    }

    public CurrencyRate() {
        this.date = LocalDate.now();
        this.currencyCode = "";
        this.rate = 0.0;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "CurrencyRate{date=" + date + ", currencyCode='" + currencyCode + "', rate=" + rate + "}";
    }
}
