package JavaSis;

public class Target {
    private int physicalDamage;
    private int fireDamage;
    private int iceDamage;

    public int getPhysicalDamage() {
        return physicalDamage;
    }

    public int getFireDamage() {
        return fireDamage;
    }

    public int getIceDamage() {
        return iceDamage;
    }

    public void attack(String weaponType, String weaponEffect) {
        if (weaponType.equals("melee")) {
            Sword sword = new Sword(weaponEffect);
            getDamage(sword);
        } else if (weaponType.equals("range")) {
            Bow bow = new Bow(weaponEffect);
            getDamage(bow);
        }
    }

    private void getDamage(Weapon w) {
        physicalDamage += w.getDamage();
        fireDamage += w.getFire();
        iceDamage += w.getIce();
    }
}
