import modell.CurrentGraphicState;
import modell.GraphicEvent;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class CurrentGraphicStateTest {
    
    public CurrentGraphicStateTest() {
    }
    CurrentGraphicState instance;
    GraphicEvent testEvent;
    
    @Before
    public void setUp() {
        instance = new CurrentGraphicState();
        testEvent = new GraphicEvent(444 ,null, null);
    }

    /**
     * Test of gameTick method, of class CurrentGraphicState.
     */
    @Test
    public void testGameTick() {
        System.out.println("gameTick");
        int currentTime = 0;
        instance.gameTick(currentTime);
        testEvent.setVisibilityTime(0, 3);
        instance.addGraphicEvent(testEvent);
        
        /*The event should not be in the stack when the time is 3*/
        instance.gameTick(3);
        
        boolean expResult = true;
        boolean result = instance.getCurrentGraphicState().isEmpty();
        assertEquals(expResult, result);
    }
    /**
     * Test of gameTick method, of class CurrentGraphicState.
     */
    @Test
    public void testGameTickFail() {
        System.out.println("gameTick");
        int currentTime = 0;
        instance.gameTick(currentTime);
        testEvent.setVisibilityTime(0, 3);
        instance.addGraphicEvent(testEvent);
        /*The event should be in the stack when the time is 2*/
        instance.gameTick(2);
        
        boolean expResult = false;
        boolean result = instance.getCurrentGraphicState().isEmpty();
        assertEquals(expResult, result);
    }

    /**
     * Test of addGraphicEvent method, of class CurrentGraphicState.
     */
    @Test
    public void testAddGraphicEvent() {
        System.out.println("addGraphicEvent");
        instance.addGraphicEvent(testEvent);
        
        boolean expResult = true;
        boolean result = instance.getCurrentGraphicState().pop().equals(testEvent);
        assertEquals(expResult, result);
    }

    /**
     * Test of removeGraphicEvent method, of class CurrentGraphicState.
     */
    @Test
    public void testRemoveGraphicEvent() {
        System.out.println("removeGraphicEvent");
        instance.addGraphicEvent(testEvent);
        
        instance.removeGraphicEvent(testEvent);
        boolean expResult = true;
        boolean result = instance.getCurrentGraphicState().isEmpty();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCurrentGraphicState method, of class CurrentGraphicState.
     */
    @Test
    public void testGetCurrentGraphicState() {
        System.out.println("getCurrentGraphicState");
        instance.addGraphicEvent(testEvent);
        instance.addGraphicEvent(testEvent);
        instance.addGraphicEvent(new GraphicEvent(33, null, null));
        instance.addGraphicEvent(new GraphicEvent(33, null, null));
        instance.addGraphicEvent(new GraphicEvent(33, null, null));
        instance.addGraphicEvent(new GraphicEvent(44, null, null));
        instance.addGraphicEvent(new GraphicEvent(33, null, null));
        instance.addGraphicEvent(testEvent);
        instance.addGraphicEvent(new GraphicEvent(44, null, null));
        
        int expResult = 3;
        int result = instance.getCurrentGraphicState().size();
        assertEquals(expResult, result);
    }
}
