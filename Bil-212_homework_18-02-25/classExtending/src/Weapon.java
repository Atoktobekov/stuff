class Weapon {
    protected String name;
    protected int damage;
    protected int ammo;

    public Weapon(String name, int damage, int ammo) {
        this.name = name;
        this.damage = damage;
        this.ammo = ammo;
    }

    public void shoot() {
        if (ammo > 0) {
            System.out.println(name + " fires! Damage: " + damage);
            ammo--;
            System.out.println("Ammo: " + ammo);

        } else {
            System.out.println(name + " is out of ammo!");
        }
    }

    public void reload(int ammo) {
        this.ammo += ammo;
        System.out.println(name + " reloaded. Ammo: " + this.ammo);
    }
}

class Minigun extends Weapon {
    private int fireRate;
    public Minigun() {
        super("Minigun", 5, 100);
        this.fireRate = 10;
    }

    public void rapidFire() {
        System.out.println(name + " starts rapid fire!");
        for (int i = 0; i < fireRate; i++) {
            shoot();
        }
    }

    public void coolDown() {
        System.out.println(name + " is cooling down...");
    }
}



