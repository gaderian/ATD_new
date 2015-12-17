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
 * main.java.main.tile.main.tile.
 * Telepoter is a class that extends the 
 * main.java.main.tile.main.tile.PathTile class. The class have a own
 * image that will be used in the user interface. 
 *
 */
public class Teleporter extends PathTile {

    private Position endTelePos = null;
    
    /**
     * main.java.main.tile.main.tile. Teleporter is the constructor that
     * will read in the image when the program will create a object
     * of the class main.java.main.tile.main.tile.Default
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
