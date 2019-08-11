package JavaSis;

public class Sword extends Weapon {
    @Override
    protected void attackLevel() {
        physicalDamage = 100;
        fireDamage = 150;
        iceDamage = 140;
    }

}
