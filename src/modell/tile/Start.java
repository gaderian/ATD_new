package modell.tile;/*
 * Start
 * 
 * Date 29/11- 2015
 * @author id12jwn
 */


import modell.Position;
import modell.unit.Unit;

import java.net.MalformedURLException;

/**
 * Start is a class that extends the PathTile class. The class
 * have a own image that will be used in the user interface.
 */
public class Start extends PathTile {

    private Position nextPos;
    /**
     * Start is the constructor that will send it's position to the
     * class PathTile, and also its image Path
     *
     * @param p the position of the tile
     * @throws MalformedURLException if something goes wrong with the image url
     */
    public Start(Position p) throws MalformedURLException {
        super(p, "pathTile.png");
    }

    /**
     * landOn will set the next position to the tile it will go to
     *
     * @param unit, the unit that steps on the tile
     */
    @Override
    public void landOn(Unit unit) {
        unit.setNextTilePos(nextPos);
    }

    /**
     * sendToPos will set the position that the tile will send the
     * units to.
     *
     * @param posToSend, The position that the unit will be send to
     */
    @Override
    public void sendToPos(Position posToSend) {
        nextPos = posToSend;
        setNextPos(posToSend);
    }

}
