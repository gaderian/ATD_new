package modell.tile;/*
 * End
 * 
 * Date 29/11- 2015
 * @author id12jwn
 */


import modell.Position;
import modell.unit.Unit;

import java.net.MalformedURLException;

/**
 * End is a class that extends the PathTile class. The class
 * have a own image that will be used in the user interface.
 */
public class End extends PathTile {

    /**
     * End is a constructor that will set the postion and the
     * image that will be useed in the GUI
     *
     * @param p, The position of the tile
     * @throws MalformedURLException if something goes wrong with the image url
     */
    public End(Position p) throws MalformedURLException {
        super(p, "pathTile.png");
    }

    /**
     * Is not doing any thing.
     * @param posToSend, The posistion that the unit will be send to
     */
    @Override
    public void sendToPos(Position posToSend) {}

    /**
     * landOn will set that the unit has reach the goal
     * @param unit the unit which lands on the tile
     */
    @Override
    public void landOn(Unit unit) {
        unit.setHasReachedGoal(true);
    }
}
