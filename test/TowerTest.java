/* import org.junit.Test;
import main.java.Position;
import main.java.main.tower.Tower;
import main.java.main.unit.Unit;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TowerTest {
    TestUnit u;
    Tower t;


    @Test
    public void shouldCreateATower() throws Exception {
        assertTrue(Tower.class.isInstance(new TestTower(new Position(10, 10))));
    }


    /* ********** TESTS FOR: withingRange(Unit) ********** */
    /*@Test
    public void shouldReturnTrueForSamePos() throws Exception {
        unitAndTower(new Position(10, 10), new Position(10, 10));
        assertTrue(t.withinRange(u));
    }

    @Test
    public void shouldReturnTrueForWithinRangeNorth() throws Exception {
        unitAndTower(new Position(10, 0), new Position(10, 10));
        assertTrue(t.withinRange(u));
    }

    @Test
    public void shouldReturnTrueForWithinRangeDiagonally() throws Exception {
        unitAndTower(new Position(15, 15), new Position(10, 10));
        assertTrue(t.withinRange(u));
    }

    @Test
    public void shouldReturnFalseForOutOfRangeToNorth() throws Exception {
        unitAndTower(new Position(10, 0), new Position(10, 11));
        assertFalse(t.withinRange(u));
    }

    @Test
    public void shouldReturnFalseForOutOfRangeDiagonally() throws Exception {
        unitAndTower(new Position(2, 2), new Position(10, 10));
        assertFalse(t.withinRange(u));
    }

    @Test
    public void shouldReturnFalseForOutOfRangeToSouth() throws Exception {
        unitAndTower(new Position(20, 21), new Position(10, 10));
        assertFalse(t.withinRange(u));
    }

    @Test
    public void shouldReturnFalseForFlyingUnitWithinRange() throws Exception {
        t = new TestGroundTower(new Position(10, 10));
        u = new TestUnit(new Position(12, 12));
        u.setFlying(true);

        assertFalse(t.withinRange(u));
    }
    /* ****************************************** */


    /* ********** TESTS FOR: attack() ********** */
   /* @Test
    public void shouldKillTargetWithFirstShot() throws Exception {
        unitWithHealthAndTower(new Position(12, 12), 10, new Position(10, 10));
        t.setTarget(u);
        t.attack(10);
        assertFalse(u.isAlive());
    }

    @Test
    public void shouldNotKillTargetWithFirstShot() throws Exception {
        unitWithHealthAndTower(new Position(12, 12), 11, new Position(10, 10));
        t.setTarget(u);
        t.attack(10);
        assertTrue(u.isAlive());
    }
    @Test
    public void shouldNotAttackIfToSoon() throws Exception {
        unitWithHealthAndTower(new Position(12, 12), 10, new Position(10, 10));
        t.setTarget(u);
        t.attack(5);
        assertTrue(u.isAlive());
    }

    /* ***************************************** */


    /* ********** TESTS FOR: hasValidTarget() ********** */
    /*@Test
    public void shouldReturnTrueForLivingTargetWithinRange() throws Exception {
        unitWithHealthAndTower(new Position(10, 15), 25, new Position(10, 10));
        t.setTarget(u);
        assertTrue(t.hasValidTarget());
    }

    @Test
    public void shouldReturnFalseForDeadTargetWithinRange() throws Exception {
        unitWithHealthAndTower(new Position(12, 12), 0, new Position(10, 10));
        t.setTarget(u);
        assertFalse(t.hasValidTarget());
    }

    @Test
    public void shouldReturnFalseForLivingTargetOutOfRange() throws Exception {
        unitWithHealthAndTower(new Position(20, 20),10, new Position(10, 10));
        t.setTarget(u);
        assertFalse(t.hasValidTarget());
    }

    @Test
    public void shouldReturnFalseForDeadTargetOutOfRange() throws Exception {
        unitWithHealthAndTower(new Position(20, 20), 0, new Position(10, 10));
        t.setTarget(u);
        assertFalse(t.hasValidTarget());
    }
    /* ***************************************** */


    /* ********** Helping methods ********** */
    /*private void unitAndTower(Position unitPos, Position towerPos) {
        u = new TestUnit(unitPos);
        t = new TestTower(towerPos);
    }

    private void unitWithHealthAndTower(Position unitPos, int unitHealth, Position towerPos){
        unitAndTower(unitPos, towerPos);
        u.setHealth(unitHealth);
    }



    /* ********** Classes for testing ********** */
    /*protected static class TestUnit extends Unit {
        public TestUnit(Position pos){
            super(pos);
        }
    }



    protected static class TestTower extends Tower {
        public TestTower(Position pos){
            super(10, 10, 10, true, true, pos);
        }
    }

    protected static class TestGroundTower extends Tower {
        public TestGroundTower(Position pos){
            super(10, 10, 10, false, true, pos);
        }
    }
}
*/