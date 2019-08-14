package JavaSis;

public class Sword extends Weapon implements Fireable, Iceable {

    public Sword(String effect) {
        super(effect);
        super.damage = 100;
        setFireDamage();
        setIceDamage();
    }

    @Override
    public void setFireDamage() {
        if (effect.equals("fire")) {
            super.fire = 150;
        } else super.fire = 0;
    }

    @Override
    public void setIceDamage() {
        if (effect.equals("ice")) {
            super.ice = 120;
        } else super.ice = 0;
    }
}
