public class Product {
    private String name;
    private String currencyCode;
    private int price;

    public Product(String name, String currencyCode, int price) {
        this.name = name;
        this.currencyCode = currencyCode;
        this.price = price;
    }

    public Product() {
        this.name  = "";
        this.currencyCode = "";
        this.price = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{name='" + name + "', currencyCode='" + currencyCode + "', price=" + price + "}";
    }
}
