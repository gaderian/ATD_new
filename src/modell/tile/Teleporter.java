package modell.tile;/*
 * Teleporter
 * 
 * Date 29/11- 2015
 * @author id12jwn
 */

import modell.Position;
import java.net.MalformedURLException;
import modell.unit.Unit;

/**
 * java.tile.tile.
 * Telepoter is a class that extends the 
 * java.tile.tile.PathTile class. The class have a own
 * image that will be used in the user interface. 
 *
 */
public class Teleporter extends PathTile {

    private Position endTelePos = null;
    
    /**
     * java.tile.tile. Teleporter is the constructor that
     * will read in the image when the program will create a object
     * of the class java.tile.tile.Default
     */
    public Teleporter(Position p) throws MalformedURLException {
	    super(p, "teleporterTile.png");
    }

    @Override
    public void landOn(Unit unit){
        if(this.endTelePos != null){
            unit.setCurrentPosition(endTelePos);
            unit.setNextTilePos(endTelePos);
        }else{
            unit.setNextTilePos(super.getNextPos());
        }
    }

    @Override
    public void sendToPos(Position posToSend) {
        setNextPos(posToSend);
    }

    public void setEndTelePos(Position pos){
        this.endTelePos = pos;
    }
}
