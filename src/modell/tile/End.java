package modell.tile;/*
 * main.java.main.tile.main.tile.End
 * 
 * Date 29/11- 2015
 * @author id12jwn
 */


import modell.Position;
import java.net.MalformedURLException;
import modell.unit.Unit;

/**
 * main.java.main.tile.main.tile.End is a class that extends the main.java.main.tile.main.tile.PathTile class. The class
 * have a own image that will be used in the user interface.
 */
public class End extends PathTile {


    /**
     * Todo
     * main.java.main.tile.main.tile.End is the constructor that will read in the image when
     * the program will create a object of the class main.java.main.tile.main.tile.Default
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
