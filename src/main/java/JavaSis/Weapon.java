package JavaSis;

public class Weapon {
    protected int damage;
    protected int fire;
    protected int ice;
    protected String effect;
    protected String type;

    public void hit(Target target) {
        target.attack(getType(), getEffect());
    }

    public Weapon(String effect) {
        this.effect = effect;
    }

    public Weapon(String type, String effect) {
        this.type = type;
        this.effect = effect;
    }

    public int getDamage() {
        return damage;
    }

    public int getFire() {
        return fire;
    }

    public int getIce() {
        return ice;
    }

    public String getType() {
        return type;
    }

    public String getEffect() {
        return effect;
    }

}
