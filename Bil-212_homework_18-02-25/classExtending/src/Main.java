public class Main {
    public static void main(String[] args) {
        Weapon pistol = new Weapon("Pistol", 10, 15);
        Minigun minigun = new Minigun();

        pistol.shoot();
        pistol.reload(5);
        minigun.rapidFire();
        minigun.coolDown();
    }
}