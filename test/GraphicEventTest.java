import java.awt.image.BufferedImage;
import modell.GraphicEvent;
import modell.Position;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Isak Hjelt
 */
public class GraphicEventTest {
    
    public GraphicEventTest() {
    }
    
    GraphicEvent instance;
    
    @Before
    public void setUp() {
        instance = new GraphicEvent(444, new Position(55, 66), null);
    }

    /**
     * Test of setVisibilityTime method, of class GraphicEvent.
     */
    @Test
    public void testSetVisibilityTime() {
        System.out.println("setVisibilityTime");
        int timestamp = 0;
        int visibilityTime = 5;
        instance.setVisibilityTime(timestamp, visibilityTime);

        boolean expResult = true;
        boolean result = instance.shouldBeRemoved(5);
        
        assertEquals(expResult, result);
    }

    /**
     * Test of shouldBeRemoved method, of class GraphicEvent.
     */
    @Test
    public void testShouldBeRemoved() {
        System.out.println("shouldBeRemoved");
        int timestamp = 0;
        int visibilityTime = 5;
        instance.setVisibilityTime(timestamp, visibilityTime);

        boolean expResult = false;
        boolean result = instance.shouldBeRemoved(4);
        
        assertEquals(expResult, result);
    }

    /**
     * Test of isLifeTimeObject method, of class GraphicEvent.
     */
    @Test
    public void testIsLifeTimeObject() {
        System.out.println("isLifeTimeObject");
        boolean expResult = true;
        instance.setVisibilityTime(0, 3);
        
        boolean result = instance.isLifeTimeObject();
        assertEquals(expResult, result);
    }

    /**
     * Test of getId method, of class GraphicEvent.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        int expResult = 444;
        int result = instance.getId();
        assertEquals(expResult, result);
    
    }

    /**
     * Test of getPos method, of class GraphicEvent.
     */
    @Test
    public void testGetPos() {
        System.out.println("getPos");
        Position expResult = new Position(55, 66);
        Position result = instance.getPos();
        assertEquals(expResult, result);
    }
}
