public class CoffeeMaker {
    private String brand;
    private boolean isOn;
    private int waterLevel;
    private int coffee;
    private int maxWaterLevel = 1000;
    private int maxCoffeeLevel = 150;

    public CoffeeMaker(String brand) {
        this.brand = brand;
        this.isOn = false;
        this.waterLevel = 0;
        this.coffee = 0;
    }

    public CoffeeMaker(String brand, int maxWaterLevel, int maxCoffeeLevel) {
        this.brand = brand;
        this.maxWaterLevel = maxWaterLevel;
        this.maxCoffeeLevel = maxCoffeeLevel;
        this.isOn = false;
        this.waterLevel = 0;
        this.coffee = 0;
    }


    public CoffeeMaker(String brand, int maxWaterLevel) {
        this.brand = brand;
        this.maxWaterLevel = maxWaterLevel;
        this.isOn = false;
        this.waterLevel = 0;
        this.coffee = 0;
    }
    private void addCoffeeInternal(int amount) {
        if (coffee + amount > maxCoffeeLevel) {
            System.out.println("Вы пытаетесь добавить больше возможного! Максимальное количество кофе - " + maxCoffeeLevel + " грамм");
            return;
        }
        coffee += amount;
        System.out.println("Добавлено " + amount + " грамм кофе.");
    }

    private void addWaterInternal(int amount) {
        if (waterLevel + amount > maxWaterLevel) {
            System.out.println("Вы пытаетесь залить больше возможного! Максимальный объём воды - " + maxWaterLevel + " мл");
            return;
        }
        waterLevel += amount;
        System.out.println("Добавлено " + amount + " мл воды.");
    }

    public int getWaterLevel() {
        return waterLevel;
    }
    public int getCoffee(){
        return coffee;
    }
    public void turnOn() {
        if (!isOn) {
            isOn = true;
            System.out.println(brand + " кофеварка включена.");
        } else {
            System.out.println(brand + " кофеварка уже включена.");
        }
    }

    public void turnOff() {
        if (isOn) {
            isOn = false;
            System.out.println(brand + " кофеварка выключена.");
        } else {
            System.out.println(brand + " кофеварка уже выключена.");
        }
    }

    public void addWater(int amount) {
        if (amount > 0) {
            addWaterInternal(amount);
        } else {
            System.out.println("Количество воды должно быть положительным.");
        }
    }

    public void addWater(int amount, boolean notify) {
        if (amount > 0) {
            addWaterInternal(amount);
            if (notify) {
                System.out.println("Объем воды в кофеварке: " + waterLevel + " мл");
            }
        } else {
            System.out.println("Количество воды должно быть положительным.");
        }
    }

    public void addCoffee(int amount) {
        if (amount > 0) {
            addCoffeeInternal(amount);
        } else {
            System.out.println("Количество кофе должно быть положительным.");
        }
    }
    public void addCoffee(int amount, boolean notify) {
        if (amount > 0) {
            addCoffeeInternal(amount);
            if (notify) {
                System.out.println("Количество кофе в кофеварке: " + coffee + " грамм");
            }
        }
            else {
            System.out.println("Количество кофе должно быть положительным.");
            }
        }




    public void brewCoffee() {
        if (isOn) {
            if (waterLevel > 0 && coffee > 0) {
                System.out.println("Кофе готовится...");
                waterLevel = 0;
                coffee = 0;
            } else {
                System.out.println("Пожалуйста, убедитесь, что уровень воды и кофе достаточен.");
            }
        } else {
            System.out.println("Кофеварка выключена. Пожалуйста, включите её перед приготовлением кофе.");
        }
    }

    @Override
    public String toString() {
        return "Кофеварка " + brand +
                " [включена: " + isOn +
                ", уровень воды: " + waterLevel + "/" + maxWaterLevel +
                " мл, кофе: " + coffee + "/" + maxCoffeeLevel + " г]";
    }
}
