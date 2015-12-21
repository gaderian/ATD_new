package modell.tile;/*
 * PathWay
 * 
 * Date 29/11- 2015
 * @author id12jwn
 */


import modell.Position;
import modell.unit.Unit;

import java.net.MalformedURLException;

/**
 * PathWay is a class that extends the PathTile class. The class
 * have a own image that will be used in the user interface.
 */
public class PathWay extends PathTile {

    private Position nextPos;

    /**
     * PathWay is the constructor that will read in the image when
     * the program will create a object
     */
    public PathWay(Position p) throws MalformedURLException {
        super(p, "pathTile.png");
    }

    /**
     * LandOn will set the units next position
     *
     * @param unit the unit which lands on the tile
     */
    @Override
    public void landOn(Unit unit) {
        unit.setNextTilePos(nextPos);

    }


    /**
     * SendToPos is an abstract method that all classes that extends
     * pathTile will override when that type of class will be
     * created
     *
     * @param posToSend, The position that the unit will be send to
     */
    @Override
    public void sendToPos(Position posToSend) {
        nextPos = posToSend;
        setNextPos(posToSend);
    }

}
