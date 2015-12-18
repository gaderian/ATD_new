package modell.tile;/*
 * java.tile.tile.End
 * 
 * Date 29/11- 2015
 * @author id12jwn
 */


import modell.Position;
import java.net.MalformedURLException;
import modell.unit.Unit;

/**
 * java.tile.tile.End is a class that extends the java.tile.tile.PathTile class. The class
 * have a own image that will be used in the user interface.
 */
public class End extends PathTile {


    /**
     * Todo
     * java.tile.tile.End is the constructor that will read in the image when
     * the program will create a object of the class java.tile.tile.Default
     */
    public End(Position p) throws MalformedURLException {
        super(p, "pathTile.png");
    }

    @Override
    public void sendToPos(Position posToSend) {}

    @Override
    public void landOn(Unit unit) {
        unit.setHasReachedGoal(true);
    }
}
