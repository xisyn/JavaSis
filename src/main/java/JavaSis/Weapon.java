package JavaSis;

public abstract class Weapon {

    protected int physicalDamage;
    protected int fireDamage;
    protected int iceDamage;

    protected abstract void attackLevel();

    public void hit(Target target) {
        attackLevel();
        target.attack(physicalDamage, fireDamage, iceDamage);
    }
}
