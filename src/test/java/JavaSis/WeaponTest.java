package JavaSis;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class WeaponTest {
    @Test
    public void testSwordPhysicalDamage() {
        Target t = new Target();
        Weapon weapon = new Weapon("melee", "");

        weapon.hit(t);

        assertEquals(100, t.getPhysicalDamage());
    }

    @Test
    public void testBowFireDamage() {
        Target t = new Target();
        Weapon weapon = new Weapon("range", "fire");

        weapon.hit(t);

        assertEquals(90, t.getFireDamage());
    }

    @Test
    public void testSwordIceDamage() {
        Target t = new Target();
        Weapon weapon = new Weapon("melee", "ice");

        weapon.hit(t);

        assertEquals(120, t.getIceDamage());
    }

    @Test
    public void testDoubleDamage() {
        Target t = new Target();
        Weapon sword = new Weapon("melee", "fire");
        Weapon bow = new Weapon("range", "ice");

        sword.hit(t);
        bow.hit(t);

        assertEquals(150, t.getPhysicalDamage());
        assertEquals(150, t.getFireDamage());
        assertEquals(70, t.getIceDamage());

    }
}
