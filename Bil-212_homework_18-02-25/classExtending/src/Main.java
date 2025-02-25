public class Main {
    public static void main(String[] args) {
        Weapon pistol = new Weapon("Pistol", 10, 15);
        Minigun minigun = new Minigun(10);

        pistol.setName("Kolt 1911");
        System.out.println(pistol.toString());
        pistol.shoot();
        pistol.reload(6);

        minigun.setName("Maksim");
        System.out.println(minigun.toString());
        minigun.shoot();
    }
}