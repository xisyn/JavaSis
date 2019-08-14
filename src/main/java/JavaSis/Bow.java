package JavaSis;

public class Bow extends Weapon implements Fireable, Iceable {

    public Bow(String effect) {
        super(effect);
        super.damage = 50;
        setFireDamage();
        setIceDamage();
    }

    @Override
    public void setFireDamage() {
        if (effect.equals("fire")) {
            super.fire = 90;
        } else super.fire = 0;
    }

    @Override
    public void setIceDamage() {
        if (effect.equals("ice")) {
            super.ice = 70;
        } else super.ice = 0;
    }
}
