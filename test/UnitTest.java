import modell.Position;
import modell.unit.AirUnit;
import modell.unit.GroundUnit;
import modell.unit.Unit;
import modell.unit.UnitForTesting;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;


/**
 *
 * @author Isak Hjelt
 */
public class UnitTest {

    Unit instance;

    @Before
    public void setUp() {
        Position pos = new Position(11, 22);
        this.instance = new AirUnit(pos, 88);

    }

    /**
     * Test of move method, of class Unit.
     */
    @Test
    public void testMove() {
        System.out.println("move");
        Position tempTilePos = new Position(0, 0);
        Unit testUnit = new UnitForTesting(new Position(0, 54/2), 1000);
        testUnit.setNextTilePos(tempTilePos);

        for(int i=0; i<52/2 ;i++){
            testUnit.move();
        }
        
        boolean expResult = true;
        boolean result = testUnit.getPosition().equals(new Position(53/2, 53/2));

        assertEquals(expResult, result);
    }

    /**
     * Test of isFlying method with flying unit, of class Unit.
     */
    @Test
    public void testIsFlyingTrue() {
        System.out.println("isFlyingTrue");
        boolean expResult = true;
        boolean result = instance.isFlying();
        assertEquals(expResult, result);
    }

    /**
     * Test of isFlying method with flying unit, of class Unit.
     */
    @Test
    public void testIsFlyingFalse() {
        System.out.println("isFlyingFalse");
        Position pos = new Position(1, 1);
        Unit instanceGround = new GroundUnit(pos,88);

        boolean expResult = false;
        boolean result = instanceGround.isFlying();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPosition method, of class Unit.
     */
    @Test
    public void testGetPosition() {
        System.out.println("getPosition");
        Position expResult = new Position(11, 22);
        Position result = instance.getPosition();
        assertEquals(expResult, result);
    }

    /**
     * Test of setCurrentPosition method, of class Unit.
     */
    @Test
    public void testSetCurrentPosition() {
        System.out.println("setCurrentPosition");
        Position pos = new Position(4,4);
        instance.setCurrentPosition(pos);
        boolean expResult = true;
        boolean result = instance.getPosition().equals
        (new Position(((54*4) +(54/2))-1, ((54*4) +(54/2))-1));
        
        System.out.println(instance.getPosition().getX());
         System.out.println(instance.getPosition().getY());
        
        assertEquals(expResult, result);
    }

    /**
     * Test of setNextPos method, of class Unit.
     */
    @Test
    public void testSetNextTilePos() {
        System.out.println("setNextPos");
        Position tempTilePos = new Position(2, 0);
        Unit testUnit = new UnitForTesting(new Position(0, 54/2), 1000);
        testUnit.setNextTilePos(tempTilePos);

        for(int i=0; i< ((54*2)+(54/2))-1 ;i++){
            testUnit.move();
        }
        
        boolean expResult = true;
        boolean result = testUnit.getPosition().equals
        (new Position(((54*2)+(54/2))-1, (54/2)-1));

        assertEquals(expResult, result);
    }
     /**
     * Test of setNextPos method, of class Unit.
     */
    @Test
    public void testSetNextTilePosFail() {
        System.out.println("setNextPos");
        Position tempTilePos = new Position(2, 0);
        Unit testUnit = new UnitForTesting(new Position(0, 54/2), 1000);
        testUnit.setNextTilePos(tempTilePos);

        for(int i=0; i< ((54*2)+(54/2))-2 ;i++){
            testUnit.move();
        }
        
        boolean expResult = false;
        boolean result = testUnit.getPosition().equals(new Position(((54*2)+(54/2))-1, (54/2)-1));

        assertEquals(expResult, result);
    }


    /**
     * Test of takeDamage method, of class Unit.
     */
    @Test
    public void testTakeDamageZero() {
        System.out.println("takeDamageZero");
        Position pos = new Position(11, 22);
        Unit testUnit = new UnitForTesting(pos,88);
        int dmg = 0;
        testUnit.takeDamage(dmg);
        /*Should be alive after 0 dmg dealt*/
        assertTrue(testUnit.isAlive());
    }
    /**
     * Test of takeDamage method, of class Unit.
     */
    @Test
    public void testTakeDamageOne() {
        System.out.println("takeDamageOne");
        Position pos = new Position(11, 22);
        Unit testUnit = new UnitForTesting(pos,88);

        int dmg = 1;
        testUnit.takeDamage(dmg);
        /*Should be dead after 1 dmg dealt since UnitForTesting only got 1 hp*/
        assertFalse(testUnit.isAlive());
    }
}
