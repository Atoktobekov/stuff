class Weapon {
    private String name;
    private int damage;
    private int ammo;

    public Weapon(String name, int damage, int ammo) {
        this.name = name;
        this.damage = damage;
        this.ammo = ammo;
    }

    public String getName() {
        return name;
    }

    public int getDamage() {
        return damage;
    }

    public int getAmmo() {
        return ammo;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDamage(int damage) {
        if (damage > 0) {
            this.damage = damage;
        } else {
            System.out.println("Урон должен быть положительным числом!");
        }
    }

    public void setAmmo(int ammo) {
        if (ammo >= 0) {
            this.ammo = ammo;
        } else {
            System.out.println("Патроны не могут быть отрицательными!");
        }
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
        if (ammo > 0) {
            this.ammo += ammo;
            System.out.println(name + " reloaded. Ammo: " + this.ammo);
        } else {
            System.out.println("Нельзя перезарядить отрицательное или нулевое количество патронов!");
        }
    }

    public void reload() {
        this.ammo += 10;
        System.out.println(name + " reloaded with default 10 bullets. Ammo: " + this.ammo);
    }

    @Override
    public String toString() {
        return "Weapon: " + name + " | Damage: " + damage + " | Ammo: " + ammo;
    }
}

class Minigun extends Weapon {
    private int fireRate;

    public Minigun(int fireRate) {
        super("Minigun", 5, 100);
        this.fireRate = fireRate;
    }

    public int getFireRate() {
        return fireRate;
    }

    public void setFireRate(int fireRate) {
        if (fireRate > 0) {
            this.fireRate = fireRate;
        } else {
            System.out.println("Скорострельность должна быть положительным числом!");
        }
    }

    public void shoot(int times) {
        System.out.println(getName() + " fires " + times + " times!");
        for (int i = 0; i < times; i++) {
            super.shoot();
        }
    }

    @Override
    public void shoot() {
        System.out.println(getName() + " fires powerful shot! Damage: " + (getDamage() * 2));
        setAmmo(getAmmo() - 1);
    }

    public void rapidFire() {
        System.out.println(getName() + " starts rapid fire!");
        shoot(fireRate);
        coolDown();
    }

    private void coolDown() {
        System.out.println(getName() + " is cooling down...");
    }

    @Override
    public String toString() {
        return super.toString() + " | Fire Rate: " + fireRate;
    }
}
