import modell.Position;
import modell.tile.*;
import org.junit.Test;

import java.net.MalformedURLException;

import static org.junit.Assert.assertNotNull;

/**
 * TileTest is a TestClass that will test that the different
 * TileClasses is working as it should. 
 */
public class TileTest {

    /**
     * Test that you can create all kind of tiles
     */
    @Test
    public void createTiles(){
        Position pos = new Position(0,0);
        Position nextPos1 = new Position(0,1);
        Position nextPos2 = new Position(1,0);

        String imgPath = "pathTile.png";

        Tile t = null;
        try {
            t = new Tile(pos, imgPath);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        End endT = null;
        try {
            endT = new End(pos);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        PathWay pathT = null;
        try {
            pathT = new PathWay(pos);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        Start startT = null;
        try {
            startT = new Start(pos);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        TCross tCross = null;
        try {
            tCross = new TCross(pos);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        Teleporter teleT = null;
        try {
            teleT = new Teleporter(pos);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        TowerTile towerT = null;
        try {
            towerT = new TowerTile(pos);
        } catch (MalformedURLException e){
            e.printStackTrace();
        }

    }

    /**
     * Test that the method setPsotions is working in the class
     * TCross
     */
    @Test
    public void setPositonsInTCross(){
        Position pos = new Position(0,0);
        Position nextPos1 = new Position(0,1);
        Position nextPos2 = new Position(1,0);

        String imgPath = "TCross.png";

        TCross tCross = null;
        try {
            tCross = new TCross(pos);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        tCross.sendToPos(nextPos1);

        Position nextP = tCross.getNextPos();
        assertNotNull(nextP);
    }

}
