import modell.Position;
import modell.tower.Tower;
import modell.unit.Unit;
import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;

public class TowerTest {
    TestUnit u;
    Tower t;


    @Test
    public void shouldCreateATower() throws Exception {
        assertTrue(Tower.class.isInstance(new TestTower(new Position(10, 10))));
    }


    /* ********** TESTS FOR: withingRange(Unit) ********** */
    @Test
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
        TestUnitFlying u = new TestUnitFlying(new Position(12, 12));

        assertFalse(t.withinRange(u));
    }
    /* ****************************************** */


    /* ********** TESTS FOR: attack() ********** */
    @Test
    public void shouldKillTargetWithFirstShot() throws Exception {
        unitAndTower(new Position(12, 12), new Position(10, 10));
        t.setTarget(u);
        t.attack(10);
        assertFalse(u.isAlive());
    }

    @Test
    public void shouldNotKillTargetWithFirstShot() throws Exception {
        uniAndWeakTower(new Position(12, 12), new Position(10, 10));
        t.setTarget(u);
        t.attack(10);
        assertTrue(u.isAlive());
    }
    @Test
    public void shouldNotAttackIfToSoon() throws Exception {
        unitAndTower(new Position(12, 12), new Position(10, 10));
        t.setTarget(u);
        t.attack(5);
        assertTrue(u.isAlive());
    }

    /* ***************************************** */


    /* ********** TESTS FOR: hasValidTarget() ********** */
    @Test
    public void shouldReturnTrueForLivingTargetWithinRange() throws Exception {
        unitAndTower(new Position(10, 15), new Position(10, 10));
        t.setTarget(u);
        assertTrue(t.hasValidTarget());
    }

    @Test
    public void shouldReturnFalseForDeadTargetWithinRange() throws Exception {
        unitAndTower(new Position(12, 12), new Position(10, 10));
        u.takeDamage(10);
        t.setTarget(u);
        assertFalse(t.hasValidTarget());
    }

    @Test
    public void shouldReturnFalseForLivingTargetOutOfRange() throws Exception {
        unitAndTower(new Position(20, 20), new Position(10, 10));
        t.setTarget(u);
        assertFalse(t.hasValidTarget());
    }

    @Test
    public void shouldReturnFalseForDeadTargetOutOfRange() throws Exception {
        unitAndTower(new Position(20, 20), new Position(10, 10));
        t.setTarget(u);
        assertFalse(t.hasValidTarget());
    }
    /* ***************************************** */


    /* ********** Helping methods ********** */
    private void unitAndTower(Position unitPos, Position towerPos) {
        u = new TestUnit(unitPos);
        t = new TestTower(towerPos);
    }

    private void uniAndWeakTower(Position unitPos, Position towerPos){
        u = new TestUnit(unitPos);
        t = new TestTowerWeak(towerPos);
    }



    /* ********** Classes for testing ********** */
    protected static class TestUnit extends Unit {
        public TestUnit(Position pos){
            super(pos, 1);
        }
    }

    protected static class TestUnitFlying extends Unit {
        public TestUnitFlying(Position pos){
            super(pos, 1);
            flying = true;
        }
    }



    protected static class TestTower extends Tower {
        public TestTower(Position pos){
            super(10, 10, 10, true, true, pos);
        }
    }

    protected static class TestTowerWeak extends Tower {
        public TestTowerWeak(Position pos){
            super(10,10, 5, true, true, pos);
        }
    }

    protected static class TestGroundTower extends Tower {
        public TestGroundTower(Position pos){
            super(10, 10, 10, false, true, pos);
        }
    }
}
