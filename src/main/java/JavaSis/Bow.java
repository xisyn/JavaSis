package JavaSis;

public class Bow extends Weapon {
    @Override
    protected void attackLevel() {
        physicalDamage = 50;
        fireDamage = 120;
        iceDamage = 110;
    }

}
