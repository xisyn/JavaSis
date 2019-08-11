package JavaSis;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class WeaponTest {
    @Test
    public void testSwordPhysicalDamage() {

        Target t = new Target();
        Weapon sword = new Sword();

        sword.hit(t);

        assertEquals(100, t.getPhysicalDamage());

    }

    @Test
    public void testBowFireDamage() {

        Target t = new Target();
        Weapon bow = new Bow();

        bow.hit(t);

        assertEquals(120, t.getFireDamage());
    }

    @Test
    public void testSwordIceDamage() {

        Target t = new Target();
        Weapon sword = new Sword();

        sword.hit(t);

        assertEquals(140, t.getIceDamage());
    }

    @Test
    public void testDoubleDamage() {

        Target t = new Target();
        Weapon sword = new Sword();
        Weapon bow = new Bow();

        sword.hit(t);
        bow.hit(t);

        assertEquals(150, t.getPhysicalDamage());
        assertEquals(270, t.getFireDamage());
        assertEquals(250, t.getIceDamage());

    }
}
