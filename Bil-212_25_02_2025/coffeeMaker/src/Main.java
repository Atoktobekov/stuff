public class Main {
    public static void main(String[] args) {
        CoffeeMaker Nokia = new CoffeeMaker("Nokia", 1200, 300);
        Nokia.addCoffee(100,true);
        Nokia.addWater(400);
        Nokia.turnOn();
        System.out.println("В кофеварке " + Nokia.getWaterLevel()+ " мл воды");
        Nokia.brewCoffee();
       System.out.println( Nokia.toString());
    }
}
