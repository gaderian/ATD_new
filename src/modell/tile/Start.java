package modell.tile;/*
 * java.tile.tile.Start
 * 
 * Date 29/11- 2015
 * @author id12jwn
 */


import modell.Position;
import java.net.MalformedURLException;
import modell.unit.Unit;

/**
 * java.tile.tile.Start is a class that extends the java.tile.tile.PathTile class. The class
 * have a own image that will be used in the user interface.
 */
public class Start extends PathTile {

    private Position nextPos;
    /**
     * java.tile.tile.Start is the constructor that will read in the image when
     * the program will create a object of the class java.tile.tile.Default
     */
    public Start(Position p) throws MalformedURLException {
        super(p, "pathTile.png");
    }

    @Override
    public void landOn(Unit unit) {
        unit.setNextTilePos(nextPos);

    }
    @Override
    public void sendToPos(Position posToSend) {
        nextPos = posToSend;
        setNextPos(posToSend);
    }

}
