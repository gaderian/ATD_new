package modell.tile;/*
 * main.java.main.tile.main.tile.PathWay
 * 
 * Date 29/11- 2015
 * @author id12jwn
 */


import modell.Position;
import java.net.MalformedURLException;
import modell.unit.Unit;

/**
 * main.java.main.tile.main.tile.PathWay is a class that extends the main.java.main.tile.main.tile.PathTile class. The class
 * have a own image that will be used in the user interface.
 */
public class PathWay extends PathTile {

    private Position nextPos;
    //hej
    /**
     * main.java.main.tile.main.tile.PathWay is the constructor that will read in the image when
     * the program will create a object of the class main.java.main.tile.main.tile.Default
     */

    public PathWay(Position p) throws MalformedURLException {
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
