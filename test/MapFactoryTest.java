import org.xml.sax.SAXParseException;
import org.junit.Test;


import modell.Map;
import modell.MapFactory;
import modell.Position;
import java.io.FileNotFoundException;
import modell.tile.Tile;

import static org.junit.Assert.assertNotNull;

public class MapFactoryTest {

    @Test (expected = SAXParseException.class)
    public void shouldFailBecauseMapFileIncorrect() throws Exception {
        new MapFactory(getClass().getResource("testMapFail.xml"));
    }

    @Test (expected = NullPointerException.class)
    public void shouldFailBecauseNoFile() throws Exception {
        new MapFactory(getClass().getResource("testMapNoExist.xml"));
    }

    @Test
    public void shouldMakeMapWithOneTile() throws Exception {
        MapFactory factory = new MapFactory(getClass().getResource("test.xml"));
        Map myMap = factory.loadMap("MapWithOnlyOneTowerTile");
        myMap.printMap();

    }

    @Test
    public void shouldMakeMapWithDifferentTileTypes() throws Exception {
        MapFactory factory = new MapFactory(getClass().getResource("test.xml"));
        Map myMap = factory.loadMap("MapWithDifferentTileTypes");
        myMap.printMap();
    }

    @Test
    public void shouldAcceptDefaultMapZiggZagg() throws Exception {
        MapFactory factory = new MapFactory("levels.xml");
        Map myMap = factory.loadMap("ZiggZagg");
        myMap.printMap();
    }

    @Test
    public void shouldAcceptDefaultMapSquare() throws Exception {
        MapFactory factory = new MapFactory("levels.xml");
        Map myMap = factory.loadMap("Square");
        myMap.printMap();
    }

    @Test
    public void shouldAcceptDefaultMapJoy() throws Exception {
        MapFactory factory = new MapFactory("levels.xml");
        Map myMap = factory.loadMap("Joy");
        myMap.printMap();
    }

}