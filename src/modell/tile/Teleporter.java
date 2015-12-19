package modell.tile;/*
 * Teleporter
 * 
 * Date 29/11- 2015
 * @author id12jwn
 */

import modell.Position;
import modell.unit.Unit;

import java.net.MalformedURLException;

/**
 * Telepoter is a class that extends the 
 * main.java.main.tile.main.tile.PathTile class. The class have a own
 * image that will be used in the user interface. 
 *
 */
public class Teleporter extends PathTile {

    private Position endTelePos = null;
    
    /**
     *Teleporter is the constructor that
     * will read in the image when the program will create a object
     */
    public Teleporter(Position p) throws MalformedURLException {
	    super(p, "teleporterTile.png");
    }

    /**
     * landOn is a method that will set the next position depending on
     * if the teleporterunit has set out the endTelPos on the board.
     *
     * @param unit, the unit that will step on the tile
     */
    @Override
    public void landOn(Unit unit){
        if(this.endTelePos != null){
            unit.setCurrentPosition(endTelePos);
            unit.setNextTilePos(endTelePos);
        }else{
            unit.setNextTilePos(super.getNextPos());
        }
    }

    /**
     * sendToPos is method that will set next position that is the
     * next position
     *
     * @param posToSend, The posistion that the unit will be send to
     */
    @Override
    public void sendToPos(Position posToSend) {
        setNextPos(posToSend);
    }

    /**
     * setEndTelePos is a method that will set the end of the teleport
     *
     * @param pos, the positon to set
     */
    public void setEndTelePos(Position pos){
        this.endTelePos = pos;
    }

}
