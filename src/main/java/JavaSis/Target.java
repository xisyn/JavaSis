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

    public void attack(int physicalAttack, int fireAttack, int iceAttack) {
        physicalDamage += physicalAttack;
        fireDamage += fireAttack;
        iceDamage += iceAttack;
    }
}
